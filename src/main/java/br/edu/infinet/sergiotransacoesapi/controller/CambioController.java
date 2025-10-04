package br.edu.infinet.sergiotransacoesapi.controller;

import br.edu.infinet.sergiotransacoesapi.model.domain.CambioQueryResult;
import br.edu.infinet.sergiotransacoesapi.model.service.CambioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/cambio")
public class CambioController {

    private final CambioService cambioService;

    public CambioController(CambioService cambioService) {
        this.cambioService = cambioService;
    }

    @GetMapping("/conversor-moedas/{saldo}")
    public ResponseEntity<CambioQueryResult> converterSaldo(@PathVariable Double saldo) {
        CambioQueryResult resultadoCambio = cambioService.converterSaldo(saldo);
        return ResponseEntity.ok(resultadoCambio);
    }
}

