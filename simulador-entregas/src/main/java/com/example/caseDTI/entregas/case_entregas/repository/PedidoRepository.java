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
    AND (pd.destinoX =:destinoX OR :destinoX IS NULL)
    AND (pd.destinoY =:destinoY OR :destinoY IS NULL)
    AND (pd.statusPedido = :status OR :status IS NULL)
    AND (pd.prioridadePedido = :prioridade OR :prioridade IS NULL)
    """)
    List<Pedido> findByAttributes (@Param("codPedido") Long id,
                                   @Param("destinoX") Integer destinoX,
                                   @Param("destinoY") Integer destinoY,
                                   @Param("status") StatusPedidoEnum statusPedido,
                                   @Param("prioridade") PrioridadePedidoEnum prioridadePedido);
    @Query("""
            SELECT pd FROM Pedido pd
            WHERE pd.statusPedido = 'PENDENTE'
            ORDER BY pd.prioridadePedido
            """)
    List<Pedido> findPedidosPendentes();

}
