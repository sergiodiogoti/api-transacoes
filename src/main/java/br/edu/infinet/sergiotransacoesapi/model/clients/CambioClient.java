package br.edu.infinet.sergiotransacoesapi.model.clients;

import br.edu.infinet.sergiotransacoesapi.model.domain.CambioApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "cambioClient", url = "${cambio.client.url}")
public interface CambioClient {
    @GetMapping("/json/last/USD-BRL,EUR-BRL")
    CambioApiResponse getCotacoes();
}
