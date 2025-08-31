package com.example.caseDTI.entregas.case_entregas.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double pesoSuportado;

    @Column
    private Integer autonomiaVoo;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusDroneEnum statusDrone;
}
