package com.example.caseDTI.entregas.case_entregas.repository;

import com.example.caseDTI.entregas.case_entregas.entity.Pedido;
import com.example.caseDTI.entregas.case_entregas.entity.PrioridadePedidoEnum;
import com.example.caseDTI.entregas.case_entregas.entity.StatusPedidoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PedidoRepository extends JpaRepository <Pedido, Long>{

    @Query( """
    SELECT pd FROM Pedido pd
    WHERE 1=1
    AND (pd.id=:codPedido OR :codePedido IS NULL)
    AND (pd.destino =:destino OR :destino IS NULL)
    AND (pd.statusPedido = :status OR :status IS NULL)
    AND (pd.prioridadePedido = :prioridade OR :prioridade IS NULL)
    """)
    List<Pedido> findByAttributes (@Param("codPedido") Long id,
                                   @Param("destino") String destino,
                                   @Param("status") StatusPedidoEnum statusPedido,
                                   @Param("prioridade") PrioridadePedidoEnum prioridadePedido);

}
