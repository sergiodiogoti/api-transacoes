package br.edu.infinet.sergiotransacoesapi.model.service;

import br.edu.infinet.sergiotransacoesapi.model.clients.CambioClient;
import br.edu.infinet.sergiotransacoesapi.model.domain.CambioApiResponse;
import br.edu.infinet.sergiotransacoesapi.model.domain.CambioQueryResult;
import br.edu.infinet.sergiotransacoesapi.model.domain.exceptions.ExternalServiceException;
import br.edu.infinet.sergiotransacoesapi.model.domain.exceptions.ResourceInvalidException;
import br.edu.infinet.sergiotransacoesapi.model.util.ConstanteUtil;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CambioService {

    private final CambioClient cambioClient;

    public CambioService(CambioClient cambioClient) {
        this.cambioClient = cambioClient;
    }

    public CambioQueryResult converterSaldo(Double saldoReais) {
        validarSaldo(saldoReais);

        CambioApiResponse cotacoes = obterCotacoes();

        double usd = parseCotacao(cotacoes.getUsd().getBid(), ConstanteUtil.USD);
        double eur = parseCotacao(cotacoes.getEur().getBid(), ConstanteUtil.EURO);

        double valorUsd = saldoReais / usd;
        double valorEur = saldoReais / eur;

        return new CambioQueryResult(saldoReais, valorUsd, valorEur);
    }

    private void validarSaldo(Double saldoReais) {
        if (saldoReais == null || saldoReais <= 0) {
            throw new ResourceInvalidException(ConstanteUtil.ERRO_SALDO_INFORMADO_DEVE_SER_MAIOR_QUE_ZERO);
        }
    }

    private CambioApiResponse obterCotacoes() {
        try {
            CambioApiResponse response = cambioClient.getCotacoes();

            if (response == null || response.getUsd() == null || response.getEur() == null) {
                throw new ExternalServiceException(
                        ConstanteUtil.ERRO_RETORNO_INCOMPLETO_INVALIDO,
                        HttpStatus.BAD_GATEWAY
                );
            }
            return response;

        } catch (FeignException.ServiceUnavailable e) {
            throw new ExternalServiceException(
                    ConstanteUtil.ERRO_SERVICO_EXTERNO_INDISPONIVEL,
                    HttpStatus.SERVICE_UNAVAILABLE
            );
        } catch (Exception e) {
            throw new ExternalServiceException(
                    ConstanteUtil.FALHA_OBTER_COTACOES + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    private double parseCotacao(String valor, String moeda) {
        return Optional.ofNullable(valor)
                .filter(v -> !v.isBlank())
                .map(Double::parseDouble)
                .orElseThrow(() -> new ResourceInvalidException(ConstanteUtil.ERRO_PARSE_COTACAO + moeda));
    }
}