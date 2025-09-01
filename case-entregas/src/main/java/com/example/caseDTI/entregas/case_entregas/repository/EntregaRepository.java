package com.example.caseDTI.entregas.case_entregas.repository;

import com.example.caseDTI.entregas.case_entregas.entity.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {

    @Query("""
            SELECT e FROM Entrega e
            WHERE 1=1
            AND (e.id =: codEntrega  OR :codEntrega IS NULL)
            AND (e.prazo = : prazo OR :prazo IS NULL)
            AND (e.pedido.id = :codPedido OR :codPedido IS NULL)
            AND (e.drone.id = :codDrone OR :codDrone IS NULL)
            """)
    List<Entrega> findByAttributes(@Param("codEntrega") Long id,
                                   @Param("prazo") Integer prazo,
                                   @Param("codPedido") Long pedido,
                                   @Param("codDrone") Long drone);

    @Query("""
            SELECT e FROM Entrega e
            WHERE e.statusEntrega = 'PENDENTE'
            """)
    List<Entrega> findEntregasPendentes();


}