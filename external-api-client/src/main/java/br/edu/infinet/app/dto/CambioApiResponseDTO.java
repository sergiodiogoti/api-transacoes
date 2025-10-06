package br.edu.infinet.app.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class CambioApiResponseDTO {
    @JsonProperty("USDBRL")
    private CotacaoDTO usd;

    @JsonProperty("EURBRL")
    private CotacaoDTO eur;

    public CotacaoDTO getUsd() {
        return usd;
    }

    public void setUsd(CotacaoDTO usd) {
        this.usd = usd;
    }

    public CotacaoDTO getEur() {
        return eur;
    }

    public void setEur(CotacaoDTO eur) {
        this.eur = eur;
    }
}
