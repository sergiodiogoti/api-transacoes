package br.edu.infinet.sergiotransacoesapi.model.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

public class CambioApiResponse {
    @JsonProperty("USDBRL")
    private Cotacao usd;

    @JsonProperty("EURBRL")
    private Cotacao eur;

    public Cotacao getUsd() {
        return usd;
    }

    public void setUsd(Cotacao usd) {
        this.usd = usd;
    }

    public Cotacao getEur() {
        return eur;
    }

    public void setEur(Cotacao eur) {
        this.eur = eur;
    }
}
