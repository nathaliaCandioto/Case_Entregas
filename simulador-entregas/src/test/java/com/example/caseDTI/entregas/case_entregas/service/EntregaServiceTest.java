package com.example.caseDTI.entregas.case_entregas.service;

import com.example.caseDTI.entregas.case_entregas.entity.*;
import com.example.caseDTI.entregas.case_entregas.repository.DroneRepository;
import com.example.caseDTI.entregas.case_entregas.repository.EntregaRepository;
import com.example.caseDTI.entregas.case_entregas.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EntregaServiceTest {

    @Mock
    private EntregaRepository entregaRepository;

    @Mock
    private DroneRepository droneRepository;

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private EntregaService entregaService;

    private Entrega validEntrega;
    private Pedido validPedido;
    private Drone validDrone;

    @BeforeEach
    void setUp() {
        validPedido = new Pedido();
        validPedido.setId(1L);
        validPedido.setPesoKg(5.0);
        validPedido.setDestinoX(10);
        validPedido.setDestinoY(10);
        validPedido.setStatusPedido(StatusPedidoEnum.PENDENTE);
        validPedido.setPrioridadePedido(PrioridadePedidoEnum.ALTA);

        validDrone = new Drone();
        validDrone.setId(1L);
        validDrone.setPesoSuportado(10.0);
        validDrone.setAutonomiaVoo(100);
        validDrone.setStatusDrone(StatusDroneEnum.DISPONIVEL);

        validEntrega = new Entrega();
        validEntrega.setId(1L);
        validEntrega.setPedido(validPedido);
        validEntrega.setStatusEntrega(StatusEntregaEnum.PENDENTE);
    }

    @Test
    @DisplayName("Deve listar entregas pendentes")
    void testEntregasPendentes() {
        List<Entrega> entregas = Arrays.asList(validEntrega);
        when(entregaRepository.findEntregasPendentes()).thenReturn(entregas);

        List<Entrega> result = entregaService.entregasPendentes();
        
        assertEquals(1, result.size());
        assertEquals(validEntrega, result.get(0));
        verify(entregaRepository).findEntregasPendentes();
    }

    @Test
    @DisplayName("Deve processar entregas ordenando por prioridade")
    void testProcessarEntregas() {
        Pedido pedidoBaixa = new Pedido();
        pedidoBaixa.setPrioridadePedido(PrioridadePedidoEnum.BAIXA);
        pedidoBaixa.setDestinoX(5);
        pedidoBaixa.setDestinoY(5);
        pedidoBaixa.setPesoKg(2.0);
        pedidoBaixa.setStatusPedido(StatusPedidoEnum.PENDENTE);

        validPedido.setStatusPedido(StatusPedidoEnum.PENDENTE);
        List<Pedido> pedidos = Arrays.asList(pedidoBaixa, validPedido);
        
        Drone drone1 = new Drone();
        drone1.setId(1L);
        drone1.setPesoSuportado(10.0);
        drone1.setAutonomiaVoo(100);
        drone1.setStatusDrone(StatusDroneEnum.DISPONIVEL);

        when(pedidoRepository.findPedidosPendentes()).thenReturn(pedidos);
        when(droneRepository.findAll()).thenReturn(Arrays.asList(drone1));

        entregaService.processarEntregas();
        
        verify(pedidoRepository).findPedidosPendentes();
        verify(entregaRepository).save(argThat(entrega ->
            entrega.getPedido().getPrioridadePedido() == PrioridadePedidoEnum.ALTA &&
            entrega.getStatusEntrega() == StatusEntregaEnum.EM_TRANSPORTE
        ));
    }

    @Test
    @DisplayName("Deve inserir entrega com drone disponível")
    void testInsertEntregaComDroneDisponivel() {
        when(droneRepository.findAll()).thenReturn(Arrays.asList(validDrone));
        
        entregaService.insertEntrega(validEntrega);
        
        assertEquals(StatusEntregaEnum.EM_TRANSPORTE, validEntrega.getStatusEntrega());
        assertEquals(StatusDroneEnum.EMVOO, validEntrega.getDrone().getStatusDrone());
        assertEquals(StatusPedidoEnum.EM_TRANSPORTE, validEntrega.getPedido().getStatusPedido());
        verify(entregaRepository).save(validEntrega);
    }

    @Test
    @DisplayName("Deve retornar null quando não há drone disponível")
    void testInsertEntregaSemDroneDisponivel() {
        validDrone.setStatusDrone(StatusDroneEnum.EMVOO);
        when(droneRepository.findAll()).thenReturn(Arrays.asList(validDrone));
        
        Entrega result = entregaService.insertEntrega(validEntrega);
        
        assertNull(result);
        verify(entregaRepository, never()).save(any());
    }

    @Test
    @DisplayName("Deve buscar entregas por atributos válidos")
    void testFindByAttributesValidos() {
        when(entregaRepository.findByAttributes(1L, 30, 1L, 1L))
            .thenReturn(Arrays.asList(validEntrega));

        List<Entrega> result = entregaService.findByAttributes(1L, 30, 1L, 1L);
        
        assertEquals(1, result.size());
        assertEquals(validEntrega, result.get(0));
    }

    @Test
    @DisplayName("Deve lançar exceção para atributos inválidos")
    void testFindByAttributesInvalidos() {
        assertThrows(IllegalArgumentException.class, 
            () -> entregaService.findByAttributes(-1L, 30, 1L, 1L));
        assertThrows(IllegalArgumentException.class, 
            () -> entregaService.findByAttributes(1L, -30, 1L, 1L));
    }

    @Test
    @DisplayName("Deve deletar entrega existente")
    void testDeleteEntregaExistente() {
        when(entregaRepository.findById(1L)).thenReturn(Optional.of(validEntrega));
        
        entregaService.deleteEntrega(1L);
        
        verify(entregaRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção ao tentar deletar entrega inexistente")
    void testDeleteEntregaInexistente() {
        when(entregaRepository.findById(1L)).thenReturn(Optional.empty());
        
        assertThrows(IllegalArgumentException.class, 
            () -> entregaService.deleteEntrega(1L));
    }
}
