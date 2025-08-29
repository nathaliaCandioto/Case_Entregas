package com.example.caseDTI.entregas.case_entregas.service;

import com.example.caseDTI.entregas.case_entregas.entity.Pedido;
import com.example.caseDTI.entregas.case_entregas.entity.PrioridadePedidoEnum;
import com.example.caseDTI.entregas.case_entregas.entity.StatusPedidoEnum;
import com.example.caseDTI.entregas.case_entregas.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido insertPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listPedido(Long id, String destino, StatusPedidoEnum statusPedido, PrioridadePedidoEnum prioridadePedido) {
        return pedidoRepository.findByAttributes(id, destino, statusPedido, prioridadePedido);
    }

    public void deletePedido(Long id) {
      pedidoRepository.deleteById(id);
    }


}
