package com.example.caseDTI.entregas.case_entregas.service;

import com.example.caseDTI.entregas.case_entregas.entity.Drone;
import com.example.caseDTI.entregas.case_entregas.entity.StatusDroneEnum;
import com.example.caseDTI.entregas.case_entregas.repository.DroneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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
public class DroneServiceTest {

    @Mock
    private DroneRepository droneRepository;

    @InjectMocks
    private DroneService droneService;

    private Drone validDrone;

    @BeforeEach
    void setUp() {
        validDrone = new Drone();
        validDrone.setId(1L);
        validDrone.setAutonomiaVoo(100);
        validDrone.setPesoSuportado(10.0);
    }

    @Test
    @DisplayName("Deve inserir drone com dados válidos e status DISPONIVEL")
    void testInsertDroneSuccess() {
        when(droneRepository.save(any(Drone.class))).thenAnswer(i -> i.getArgument(0));
        Drone result = droneService.insertDrone(validDrone);
        assertEquals(StatusDroneEnum.DISPONIVEL, result.getStatusDrone());
        verify(droneRepository).save(validDrone);
    }

    @Test
    @DisplayName("Deve lançar exceção se autonomia for nula")
    void testInsertDroneAutonomiaNula() {
        validDrone.setAutonomiaVoo(null);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> droneService.insertDrone(validDrone));
        assertEquals("Dados invalidos", ex.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção se peso suportado for nulo")
    void testInsertDronePesoNulo() {
        validDrone.setPesoSuportado(null);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> droneService.insertDrone(validDrone));
        assertEquals("Dados invalidos", ex.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção se autonomia for <= 0")
    void testInsertDroneAutonomiaZeroOuNegativa() {
        validDrone.setAutonomiaVoo(0);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> droneService.insertDrone(validDrone));
        assertEquals("Dados invalidos", ex.getMessage());
        validDrone.setAutonomiaVoo(-1);
        ex = assertThrows(IllegalArgumentException.class, () -> droneService.insertDrone(validDrone));
        assertEquals("Dados invalidos", ex.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção se peso suportado for <= 0")
    void testInsertDronePesoZeroOuNegativo() {
        validDrone.setPesoSuportado(0.0);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> droneService.insertDrone(validDrone));
        assertEquals("Dados invalidos", ex.getMessage());
        validDrone.setPesoSuportado(-5.0);
        ex = assertThrows(IllegalArgumentException.class, () -> droneService.insertDrone(validDrone));
        assertEquals("Dados invalidos", ex.getMessage());
    }

    @Test
    @DisplayName("Deve deletar drone existente")
    void testDeleteDroneSuccess() {
        when(droneRepository.findById(1L)).thenReturn(Optional.of(validDrone));
        droneService.deleteDrone(1L);
        verify(droneRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Deve lançar exceção ao deletar drone inexistente")
    void testDeleteDroneNotFound() {
        when(droneRepository.findById(2L)).thenReturn(Optional.empty());
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> droneService.deleteDrone(2L));
        assertEquals("Drone nao encontrado", ex.getMessage());
    }

    @Test
    @DisplayName("Deve listar drones com filtros válidos")
    void testListDroneComFiltros() {
        List<Drone> drones = Arrays.asList(validDrone);
        when(droneRepository.findByAtributes(1L, 100, StatusDroneEnum.DISPONIVEL)).thenReturn(drones);
        List<Drone> result = droneService.listDrone(1L, 100, StatusDroneEnum.DISPONIVEL);
        assertEquals(1, result.size());
        assertEquals(validDrone, result.get(0));
    }

    @Test
    @DisplayName("Deve lançar exceção se filtro codDrone inválido")
    void testListDroneCodDroneInvalido() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> droneService.listDrone(0L, 100, StatusDroneEnum.DISPONIVEL));
        assertEquals("Dados invalidos", ex.getMessage());
        ex = assertThrows(IllegalArgumentException.class, () -> droneService.listDrone(-1L, 100, StatusDroneEnum.DISPONIVEL));
        assertEquals("Dados invalidos", ex.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção se filtro autonomiaVoo inválido")
    void testListDroneAutonomiaInvalida() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> droneService.listDrone(1L, 0, StatusDroneEnum.DISPONIVEL));
        assertEquals("Dados invalidos", ex.getMessage());
        ex = assertThrows(IllegalArgumentException.class, () -> droneService.listDrone(1L, -10, StatusDroneEnum.DISPONIVEL));
        assertEquals("Dados invalidos", ex.getMessage());
    }

    @Test
    @DisplayName("Deve listar drones com lista vazia se nenhum encontrado")
    void testListDroneNenhumEncontrado() {
        when(droneRepository.findByAtributes(null, null, null)).thenReturn(Collections.emptyList());
        List<Drone> result = droneService.listDrone(null, null, null);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
