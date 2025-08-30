package com.example.caseDTI.entregas.case_entregas.repository;


import com.example.caseDTI.entregas.case_entregas.entity.Drone;
import com.example.caseDTI.entregas.case_entregas.entity.StatusDroneEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DroneRepository extends JpaRepository <Drone, Long > {

    @Query ("""
            SELECT dr FROM Drone dr
            WHERE 1=1
            AND (dr.id = :codDrone OR  :codDrone IS NULL)
            AND (dr.autonomiaVoo = :autonomiaVoo OR :autonomiaVoo IS NULL)
            AND (dr.statusDrone = :statusDrone OR :statusDrone IS NULL)
            """)

    List<Drone> findByAtributes (@Param("codDrone")Long id,
                                 @Param("autonomiaVoo") Integer autonomiaVoo,
                                 @Param("statusDrone")StatusDroneEnum statusDrone
                                 );

}
