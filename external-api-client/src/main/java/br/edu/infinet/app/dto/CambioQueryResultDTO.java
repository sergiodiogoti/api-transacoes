package br.edu.infinet.app.dto;

public class CambioQueryResultDTO {

    private Double saldoEmReais;
    private Double saldoEmUSD;
    private Double saldoEmEUR;

    public CambioQueryResultDTO(Double saldoEmReais, Double saldoEmUSD, Double saldoEmEUR) {
        this.saldoEmReais = saldoEmReais;
        this.saldoEmUSD = saldoEmUSD;
        this.saldoEmEUR = saldoEmEUR;
    }

    public Double getSaldoEmReais() {
        return saldoEmReais;
    }

    public Double getSaldoEmUSD() {
        return saldoEmUSD;
    }

    public Double getSaldoEmEUR() {
        return saldoEmEUR;
    }
}
