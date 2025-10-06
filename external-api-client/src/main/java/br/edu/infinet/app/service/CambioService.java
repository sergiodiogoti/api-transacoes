package br.edu.infinet.app.service;

import br.edu.infinet.app.client.CambioClient;
import br.edu.infinet.app.dto.CambioApiResponseDTO;
import br.edu.infinet.app.dto.CambioQueryResultDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CambioService {

    private final CambioClient cambioClient;

    public CambioService(CambioClient cambioClient) {
        this.cambioClient = cambioClient;
    }

    public CambioQueryResultDTO converterSaldo(Double saldoReais) {
        validarSaldo(saldoReais);

        CambioApiResponseDTO cotacoes = cambioClient.getCotacoes();

        double usd = parseCotacao(cotacoes.getUsd().getBid(), "USD");
        double eur = parseCotacao(cotacoes.getEur().getBid(), "EUR");

        double valorUsd = saldoReais / usd;
        double valorEur = saldoReais / eur;

        return new CambioQueryResultDTO(saldoReais, valorUsd, valorEur);
    }

    private void validarSaldo(Double saldoReais) {
        if (saldoReais == null || saldoReais <= 0) {
            throw new IllegalArgumentException("O saldo informado deve ser maior que zero.");
        }
    }

    private double parseCotacao(String valor, String moeda) {
        return Optional.ofNullable(valor)
                .filter(v -> !v.isBlank())
                .map(Double::parseDouble)
                .orElseThrow(() -> new RuntimeException("Cotação inválida para a moeda:" + moeda));
    }
}