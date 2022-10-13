package com.api.controleestacionamento.services;

import com.api.controleestacionamento.models.EstacionamentoVagaModel;
import com.api.controleestacionamento.repositories.EstacionamentoVagaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EstacionamentoVagaService {

    final EstacionamentoVagaRepository estacionamentoVagaRepository;

    public EstacionamentoVagaService(EstacionamentoVagaRepository estacionamentoVagaRepository) {
        this.estacionamentoVagaRepository = estacionamentoVagaRepository;
    }

    @Transactional
    public EstacionamentoVagaModel save(EstacionamentoVagaModel estacionamentoVagaModel) {
        return estacionamentoVagaRepository.save(estacionamentoVagaModel);

    }

    public boolean existsByPlacaCarro(String placaCarro) {
        return estacionamentoVagaRepository.existsByPlacaCarro(placaCarro);
    }

    public boolean existsByEstacionamentoVaganumero(String estacionamentoVagaNumero) {
        return estacionamentoVagaRepository.existsByEstacionamentoVagaNumero(estacionamentoVagaNumero);
    }

    public boolean existsByApartamentoAndBloco(String apartamento, String bloco) {
        return estacionamentoVagaRepository.existsByApartamentoAndBloco(apartamento, bloco);
    }

    public List<EstacionamentoVagaModel> findAll() {
        return estacionamentoVagaRepository.findAll();
    }

    public Optional<EstacionamentoVagaModel> findById(UUID id) {
        return  estacionamentoVagaRepository.findById(id);
    }

    @Transactional
    public void delete(EstacionamentoVagaModel estacionamentoVagaModel) {
        estacionamentoVagaRepository.delete(estacionamentoVagaModel);
    }
}
