package br.edu.infinet.app.client;

import br.edu.infinet.app.dto.CambioApiResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "cambioClient", url = "${cambio.client.url}")
public interface CambioClient {
    @GetMapping("/json/last/USD-BRL,EUR-BRL")
    CambioApiResponseDTO getCotacoes();
}
