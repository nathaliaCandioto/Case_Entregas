package com.example.caseDTI.entregas.case_entregas.service;

import com.example.caseDTI.entregas.case_entregas.entity.Drone;
import com.example.caseDTI.entregas.case_entregas.entity.StatusDroneEnum;
import com.example.caseDTI.entregas.case_entregas.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DroneService {

    @Autowired
    private DroneRepository droneRepository;

    public Drone insertDrone(Drone drone){
        return droneRepository.save(drone);
    }

    public void deleteDrone (Long id){
        droneRepository.deleteById(id);
    }

   public List<Drone> listDrone (Long codDrone,Integer autonomiaVoo,StatusDroneEnum statusDrone){
        return droneRepository.findByAtributes(codDrone,autonomiaVoo,statusDrone);

   }
}
