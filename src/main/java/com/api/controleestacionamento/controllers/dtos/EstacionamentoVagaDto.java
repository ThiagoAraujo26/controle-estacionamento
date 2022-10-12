package com.api.controleestacionamento.controllers.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EstacionamentoVagaDto {

    @NotBlank
    private String estacionamentoVagaNumero;
    @NotBlank
    @Size(max = 7)
    private String placaCarro;
    @NotBlank
    private String marcaCarro;
    @NotBlank
    private String modeloCarro;
    @NotBlank
    private String corCarro;
    @NotBlank
    private String responsavelNome;
    @NotBlank
    private String apartamento;
    @NotBlank
    private String bloco;

    public String getEstacionamentoVagaNumero() {
        return estacionamentoVagaNumero;
    }

    public void setEstacionamentoVagaNumero(String estacionamentoVagaNumero) {
        this.estacionamentoVagaNumero = estacionamentoVagaNumero;
    }

    public String getPlacaCarro() {
        return placaCarro;
    }

    public void setPlacaCarro(String placaCarro) {
        this.placaCarro = placaCarro;
    }

    public String getMarcaCarro() {
        return marcaCarro;
    }

    public void setMarcaCarro(String marcaCarro) {
        this.marcaCarro = marcaCarro;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public void setModeloCarro(String modeloCarro) {
        this.modeloCarro = modeloCarro;
    }

    public String getCorCarro() {
        return corCarro;
    }

    public void setCorCarro(String corCarro) {
        this.corCarro = corCarro;
    }

    public String getResponsavelNome() {
        return responsavelNome;
    }

    public void setResponsavelNome(String responsavelNome) {
        this.responsavelNome = responsavelNome;
    }

    public String getApartamento() {
        return apartamento;
    }

    public void setApartamento(String apartamento) {
        this.apartamento = apartamento;
    }

    public String getBloco() {
        return bloco;
    }

    public void setBloco(String bloco) {
        this.bloco = bloco;
    }
}

