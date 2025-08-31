package com.example.caseDTI.entregas.case_entregas.controller;

import com.example.caseDTI.entregas.case_entregas.entity.Drone;
import com.example.caseDTI.entregas.case_entregas.entity.StatusDroneEnum;
import com.example.caseDTI.entregas.case_entregas.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drone")
public class DroneController {
    @Autowired
    private DroneService droneService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Drone insertDrone (@RequestBody Drone drone){
        return droneService.insertDrone(drone);
    }

    @GetMapping
    public ResponseEntity<List<Drone>> listDrone(@RequestParam(value = "codDrone", required = false) Long id,
                                                 @RequestParam(value = "autonomiaVoo", required = false) Integer autonomiaVoo,
                                                 @RequestParam(value = "statusDrone", required = false) StatusDroneEnum statusDrone) {
        List<Drone> drones = droneService.listDrone(id, autonomiaVoo, statusDrone);
        if (drones.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(drones);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteDrone(Long codDrone){
        try{
            droneService.deleteDrone(codDrone);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();

    }


}
