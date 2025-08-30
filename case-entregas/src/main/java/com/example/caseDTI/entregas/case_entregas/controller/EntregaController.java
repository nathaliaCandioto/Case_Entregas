package com.example.caseDTI.entregas.case_entregas.controller;
import com.example.caseDTI.entregas.case_entregas.entity.Entrega;
import com.example.caseDTI.entregas.case_entregas.repository.EntregaRepository;
import com.example.caseDTI.entregas.case_entregas.service.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/entrega")
public class EntregaController {

    private final EntregaService service;

    @Autowired
    public  EntregaController (EntregaService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Entrega> insertEntrega(@RequestBody Entrega entrega) {
        Entrega entregaCriada = service.insertEntrega(entrega);
        return ResponseEntity.ok(entregaCriada);
    }

    @GetMapping
    public ResponseEntity<List<Entrega>> findByAttributes(@RequestParam(value = "id", required = false) Long id,
                                                          @RequestParam(value = "prazo", required = false) Integer prazo,
                                                          @RequestParam(value = "codPedido", required = false) Long codPedido,
                                                          @RequestParam(value = "codDrone", required = false) Long codDrone) {

        List<Entrega> entregas = service.findByAttributes(id, prazo, codPedido, codDrone);
        if (entregas.isEmpty())
            return  ResponseEntity.notFound().build();

        return  ResponseEntity.ok(entregas);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteEntrega(@PathVariable (value="id") Long id) {
        service.deleteEntrega(id);
        return ResponseEntity.noContent().build();
    }

}

