package br.edu.infinet.sergiotransacoesapi.loader;


import br.edu.infinet.sergiotransacoesapi.model.domain.CambioQueryResult;
import br.edu.infinet.sergiotransacoesapi.model.service.CambioService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CambioLoader implements ApplicationRunner {

    private final CambioService cambioService;

    public CambioLoader(CambioService cambioService) {
        this.cambioService = cambioService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Exemplo: converter R$ 1000,00
        double saldoReais = 1000.0;
        CambioQueryResult resultado = cambioService.converterSaldo(saldoReais);

        System.out.println("=== [CÂMBIO] Conversão de Saldo ===");
        System.out.println("Saldo em Reais (BRL): " + resultado.getSaldoEmReais());
        System.out.println("Saldo em Dólares (USD): " + resultado.getSaldoEmUSD());
        System.out.println("Saldo em Euros (EUR): " + resultado.getSaldoEmEUR());
        System.out.println("===================================");
    }
}

