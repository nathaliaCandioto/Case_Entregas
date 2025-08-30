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
    public ResponseEntity<Pedido> insertPedido(@RequestBody Pedido pedido) {
        Pedido pedidoCriado = pedidoService.insertPedido(pedido);
        return ResponseEntity.ok(pedidoCriado);
    }


    @GetMapping
    public ResponseEntity<List<Pedido>> findByAttributes(@RequestParam(value = "id", required = false) Long id,
                                                         @RequestParam(value = "destino", required = false) String destino,
                                                         @RequestParam(value = "statusPedido", required = false) StatusPedidoEnum statusPedido,
                                                         @RequestParam(value = "prioridade", required = false) PrioridadePedidoEnum prioridadePedido) {

        List<Pedido> pedidos = pedidoService.listPedido(id, destino, statusPedido, prioridadePedido);
        if (pedidos.isEmpty())
            return  ResponseEntity.notFound().build();

        return  ResponseEntity.ok(pedidos);
    }

    @DeleteMapping ("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePedido(@PathVariable (value="id") Long id){
         pedidoService.deletePedido(id);
    }
}
