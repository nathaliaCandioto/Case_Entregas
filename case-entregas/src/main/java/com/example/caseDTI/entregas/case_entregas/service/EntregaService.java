package com.example.caseDTI.entregas.case_entregas.service;

import com.example.caseDTI.entregas.case_entregas.entity.*;
import com.example.caseDTI.entregas.case_entregas.repository.DroneRepository;
import com.example.caseDTI.entregas.case_entregas.repository.EntregaRepository;
import com.example.caseDTI.entregas.case_entregas.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntregaService {

    private Integer origemX=0;
    private Integer origemY=0;

    private final EntregaRepository repository;
    private final DroneRepository droneRepository;
    private final PedidoRepository pedidoRepository;


    @Autowired
    public EntregaService(EntregaRepository repository, DroneRepository droneRepository, PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.droneRepository = droneRepository;
        this.repository = repository;
    }

    public Entrega insertEntrega (Entrega entrega) {
        entrega.setStatusEntrega(StatusEntregaEnum.PENDENTE);
        List<Drone> drones = droneRepository.findAll();
        Pedido pedido = pedidoRepository.findById(entrega.getPedido().getId()).get();
        entrega.setPedido(pedido);
        Integer distanciaTotal=(pedido.getDestinoX()+entrega.getPedido().getDestinoY())-(origemX+origemY);

        for (int i = 0; i< drones.size(); i++) {
            Drone d = drones.get(i);
            if (d.getPesoSuportado()>= entrega.getPedido().getPesoKg() &&
                    d.getStatusDrone().equals(StatusDroneEnum.DISPONIVEL) &&
                    d.getAutonomiaVoo()>=(distanciaTotal*2)) {
                entrega.setDrone(d);
                entrega.setStatusEntrega(StatusEntregaEnum.EM_TRANSPORTE);
                entrega.getDrone().setStatusDrone(StatusDroneEnum.EMVOO);
                entrega.getPedido().setStatusPedido(StatusPedidoEnum.EM_TRANSPORTE);
                break;
            }
        }

        return repository.save(entrega);
    }

    public List<Entrega> findByAttributes(Long id, Integer prazo, Long codPedido, Long codDrone) {
        if((id != null && id <= 0) || (prazo != null && prazo <=0) || (codPedido != null && codPedido <=0) || (codDrone != null && codDrone <=0)){

            throw new IllegalArgumentException("Dados invalidos");
        }
        return repository.findByAttributes(id, prazo, codPedido, codDrone);
    }

    public void deleteEntrega(Long id) {
        Optional<Entrega> entrega  = repository.findById(id);
        if(entrega.isEmpty()){

            throw new IllegalArgumentException("Entrega nao encontrada");
        }

        repository.deleteById(id);
    }

}