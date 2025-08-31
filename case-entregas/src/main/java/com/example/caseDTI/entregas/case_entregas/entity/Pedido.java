package com.example.caseDTI.entregas.case_entregas.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double pesoKg;

    @Column
    private Integer destinoX;
    @Column
    private Integer destinoY;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusPedidoEnum statusPedido;

    @Column
    @Enumerated(EnumType.STRING)
    private PrioridadePedidoEnum prioridadePedido;

}
