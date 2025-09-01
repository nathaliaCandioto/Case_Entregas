package com.example.caseDTI.entregas.case_entregas.controller;

import com.example.caseDTI.entregas.case_entregas.entity.Entrega;
import com.example.caseDTI.entregas.case_entregas.service.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entrega")
public class EntregaController {

    private final EntregaService service;

    @Autowired
    public EntregaController(EntregaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> insertEntrega() {
        service.processarEntregas();
        return ResponseEntity.status(201).build();
    }

    @GetMapping
    public ResponseEntity<List<Entrega>> findByAttributes(@RequestParam(value = "id", required = false) Long id,
                                                          @RequestParam(value = "prazo", required = false) Integer prazo,
                                                          @RequestParam(value = "codPedido", required = false) Long codPedido,
                                                          @RequestParam(value = "codDrone", required = false) Long codDrone) {

        try {
            service.findByAttributes(id, prazo, codPedido, codDrone);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.findByAttributes(id, prazo, codPedido, codDrone));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntrega(@PathVariable(value = "id") Long id) {
        try {
            service.deleteEntrega(id);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();

        }
        return ResponseEntity.noContent().build();
    }


}

