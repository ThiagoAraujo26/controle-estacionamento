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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<List<EstacionamentoVagaModel>> getAllEstacionamentoVagas(){
        return ResponseEntity.status(HttpStatus.OK).body(estacionamentoVagaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneEstacionamentoVaga(@PathVariable(value = "id") UUID id){
        Optional<EstacionamentoVagaModel> estacionamentoVagaModelOptional = estacionamentoVagaService.findById(id);
        if (!estacionamentoVagaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga de Estacionamento não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(estacionamentoVagaModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEstacionamentoVaga(@PathVariable(value = "id") UUID id){
        Optional<EstacionamentoVagaModel> estacionamentoVagaModelOptional = estacionamentoVagaService.findById(id);
        if (!estacionamentoVagaModelOptional.isPresent()){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga de Estacionamento não encontrada.");
    }
        estacionamentoVagaService.delete(estacionamentoVagaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Vaga de estacionamento deletada com sucesso.");
    }



}
