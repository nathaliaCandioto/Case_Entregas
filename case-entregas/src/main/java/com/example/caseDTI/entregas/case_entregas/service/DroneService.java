package com.example.caseDTI.entregas.case_entregas.service;

import com.example.caseDTI.entregas.case_entregas.entity.Drone;
import com.example.caseDTI.entregas.case_entregas.entity.StatusDroneEnum;
import com.example.caseDTI.entregas.case_entregas.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DroneService {

    @Autowired
    private DroneRepository droneRepository;

    public Drone insertDrone(Drone drone) {
        if (drone.getAutonomiaVoo() == null || drone.getPesoSuportado() == null || drone.getPesoSuportado() <= 0 || drone.getAutonomiaVoo() <= 0) {
            throw new IllegalArgumentException("Dados invalidos");
        }
        drone.setStatusDrone(StatusDroneEnum.DISPONIVEL);
        return droneRepository.save(drone);
    }

    public void deleteDrone(Long id) {
        Optional<Drone> droneIndisponivel = droneRepository.findById(id);
        if (droneIndisponivel.isEmpty()) {
            throw new IllegalArgumentException("Drone nao encontrado");
        }
        droneRepository.deleteById(id);
    }

    public List<Drone> listDrone(Long codDrone, Integer autonomiaVoo, StatusDroneEnum statusDrone) {
        if((codDrone != null && codDrone <= 0) || (autonomiaVoo != null && autonomiaVoo <=0)){

            throw new IllegalArgumentException("Dados invalidos");
        }

        return droneRepository.findByAtributes(codDrone, autonomiaVoo, statusDrone);


    }

}
