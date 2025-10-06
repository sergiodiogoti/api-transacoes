package br.edu.infinet.app.controller;


import br.edu.infinet.app.dto.CambioQueryResultDTO;
import br.edu.infinet.app.service.CambioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cambio")
public class TransacaoCambioController {

    private final CambioService cambioService;

    public TransacaoCambioController(CambioService cambioService) {
        this.cambioService = cambioService;
    }


    @GetMapping("/conversor-moedas/{saldo}")
    public ResponseEntity<CambioQueryResultDTO> converterSaldo(@PathVariable Double saldo) {
        CambioQueryResultDTO resultadoSaldoEmMoedas = cambioService.converterSaldo(saldo);
        return ResponseEntity.ok(resultadoSaldoEmMoedas);
    }
}
