package com.example.caseDTI.entregas.case_entregas.service;

import com.example.caseDTI.entregas.case_entregas.entity.Entrega;
import com.example.caseDTI.entregas.case_entregas.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregaService {

    private final EntregaRepository repository;

    @Autowired
    public EntregaService(EntregaRepository repository) {
        this.repository = repository;
    }


    public Entrega insertEntrega (Entrega entrega) {
        return repository.save(entrega);
    }

    public List<Entrega> findByAttributes(Long id, Integer prazo, Long codPedido, Long codDrone) {
        return repository.findByAttributes(id, prazo, codPedido, codDrone);
    }

    public void deleteEntrega(Long id) {
        repository.deleteById(id);
    }

}