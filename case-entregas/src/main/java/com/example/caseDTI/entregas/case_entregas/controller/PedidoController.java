package com.example.caseDTI.entregas.case_entregas.controller;

import com.example.caseDTI.entregas.case_entregas.entity.Pedido;
import com.example.caseDTI.entregas.case_entregas.entity.PrioridadePedidoEnum;
import com.example.caseDTI.entregas.case_entregas.entity.StatusPedidoEnum;
import com.example.caseDTI.entregas.case_entregas.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> insertProduto(@RequestBody Pedido pedido) {
        Pedido pedido1 = pedidoService.insertPedido(pedido);
        return ResponseEntity.ok(pedido1);
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> findByAttributes(@RequestParam(value = "id", required = false) Long id,
                                                         @RequestParam(value = "destino", required = false) String destino,
                                                         @RequestParam(value = "statusPedido", required = false) StatusPedidoEnum statusPedido,
                                                         @RequestParam(value = "prioridade", required = false) PrioridadePedidoEnum prioridadePedido) {

        List<Pedido> pedidos = pedidoService.listingPedido(id, destino, statusPedido, prioridadePedido);
        if (pedidos.isEmpty())
            return  ResponseEntity.notFound().build();

        return  ResponseEntity.ok(pedidos);
    }

}
