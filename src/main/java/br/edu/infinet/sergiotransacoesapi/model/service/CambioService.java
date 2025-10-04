package br.edu.infinet.sergiotransacoesapi.model.service;

import br.edu.infinet.sergiotransacoesapi.model.clients.CambioClient;
import br.edu.infinet.sergiotransacoesapi.model.domain.CambioApiResponse;
import br.edu.infinet.sergiotransacoesapi.model.domain.CambioQueryResult;
import org.springframework.stereotype.Service;

@Service
public class CambioService {

    private final CambioClient cambioClient;

    public CambioService(CambioClient cambioClient) {
        this.cambioClient = cambioClient;
    }

    public CambioQueryResult converterSaldo(Double saldoReais) {
        CambioApiResponse cotacoes = cambioClient.getCotacoes();

        double usd = Double.parseDouble(cotacoes.getUsd().getBid());
        double eur = Double.parseDouble(cotacoes.getEur().getBid());

        return new CambioQueryResult(
                saldoReais,
                saldoReais / usd,
                saldoReais / eur
        );
    }
}

