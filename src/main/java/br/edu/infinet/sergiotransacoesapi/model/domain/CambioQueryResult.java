package br.edu.infinet.sergiotransacoesapi.model.domain;

public class CambioQueryResult {

    private Double saldoEmReais;
    private Double saldoEmUSD;
    private Double saldoEmEUR;

    public CambioQueryResult(Double saldoEmReais, Double saldoEmUSD, Double saldoEmEUR) {
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
