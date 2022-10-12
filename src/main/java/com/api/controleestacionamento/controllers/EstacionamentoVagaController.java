package com.api.controleestacionamento.controllers;

import com.api.controleestacionamento.controllers.dtos.EstacionamentoVagaDto;
import com.api.controleestacionamento.models.EstacionamentoVagaModel;
import com.api.controleestacionamento.services.EstacionamentoVagaService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/estacionamento-vaga")
public class EstacionamentoVagaController {

    final EstacionamentoVagaService estacionamentoVagaService;

    public EstacionamentoVagaController(EstacionamentoVagaService estacionamentoVagaService) {
        this.estacionamentoVagaService = estacionamentoVagaService;
    }

    @PostMapping
    public ResponseEntity<Object> saveEstacionamentoVaga(@RequestBody @Valid EstacionamentoVagaDto estacionamentoVagaDto){
        if(estacionamentoVagaService.existsByPlacaCarro(estacionamentoVagaDto.getPlacaCarro())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Placa do carro já está em uso!!");
        }
        if(estacionamentoVagaService.existsByEstacionamentoVaganumero(estacionamentoVagaDto.getEstacionamentoVagaNumero())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Vaga de estacionamento já está em uso!!");
        }
        if(estacionamentoVagaService.existsByApartamentoAndBloco(estacionamentoVagaDto.getApartamento(), estacionamentoVagaDto.getBloco())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Vaga de estacionamento já está em uso para apartamento/bloco");
        }
        var estacionamentoVagaModel = new EstacionamentoVagaModel();
        BeanUtils.copyProperties(estacionamentoVagaDto, estacionamentoVagaModel);
        estacionamentoVagaModel.setRegistroData(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(estacionamentoVagaService.save(estacionamentoVagaModel));
    }


}
