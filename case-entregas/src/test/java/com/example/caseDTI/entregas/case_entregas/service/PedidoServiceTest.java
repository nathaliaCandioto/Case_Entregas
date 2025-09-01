package com.example.caseDTI.entregas.case_entregas.service;

import com.example.caseDTI.entregas.case_entregas.entity.Pedido;
import com.example.caseDTI.entregas.case_entregas.entity.PrioridadePedidoEnum;
import com.example.caseDTI.entregas.case_entregas.entity.StatusPedidoEnum;
import com.example.caseDTI.entregas.case_entregas.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    private Pedido validPedido;

    @BeforeEach
    void setUp() {
        validPedido = new Pedido();
        validPedido.setId(1L);
        validPedido.setPesoKg(5.0);
        validPedido.setDestinoX(10);
        validPedido.setDestinoY(10);
        validPedido.setStatusPedido(StatusPedidoEnum.PENDENTE);
        validPedido.setPrioridadePedido(PrioridadePedidoEnum.ALTA);
    }

    @Test
    @DisplayName("Deve inserir pedido com dados válidos")
    void testInsertPedidoSuccess() {
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(validPedido);
        
        Pedido result = pedidoService.insertPedido(validPedido);
        
        assertNotNull(result);
        assertEquals(validPedido.getId(), result.getId());
        assertEquals(validPedido.getPesoKg(), result.getPesoKg());
        verify(pedidoRepository).save(validPedido);
    }

    @Test
    @DisplayName("Deve listar pedidos com filtros válidos")
    void testListPedidoComFiltros() {
        when(pedidoRepository.findByAttributes(1L, 10, 10, StatusPedidoEnum.PENDENTE, PrioridadePedidoEnum.ALTA))
            .thenReturn(Arrays.asList(validPedido));

        List<Pedido> result = pedidoService.listPedido(1L, 10, 10, StatusPedidoEnum.PENDENTE, PrioridadePedidoEnum.ALTA);
        
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(validPedido, result.get(0));
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não encontrar pedidos")
    void testListPedidoSemResultados() {
        when(pedidoRepository.findByAttributes(any(), any(), any(), any(), any()))
            .thenReturn(Arrays.asList());

        List<Pedido> result = pedidoService.listPedido(null, null, null, null, null);
        
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Deve deletar pedido existente")
    void testDeletePedido() {
        pedidoService.deletePedido(1L);
        verify(pedidoRepository).deleteById(1L);
    }
}
