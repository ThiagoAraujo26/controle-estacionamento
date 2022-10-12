package com.api.controleestacionamento.repositories;

import com.api.controleestacionamento.models.EstacionamentoVagaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstacionamentoVagaRepository extends JpaRepository<EstacionamentoVagaModel, UUID> {

    boolean existsByPlacaCarro(String placaCarro);
    boolean existsByEstacionamentoVagaNumero(String estacionamentoVagaNumero);
    boolean existsByApartamentoAndBloco(String apartamento, String bloco);
}
