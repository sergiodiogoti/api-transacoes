## 🔗 Demonstração da Integração entre Microsserviços

Esta Feature demonstra a **interoperabilidade entre sistemas**:
- O **Microserviço de Transacao (Feature 2)** fornece dados de conversão de moedas em tempo real.
- O **Projeto de Gestão Financeira (disciplina anterior)** consome esses dados via **Spring Cloud OpenFeign** e os utiliza como parte de suas funcionalidades.

📂 **Repositório do Projeto da Disciplina Anterior (consumidor):**  
👉 [https://github.com/sergiodiogoti/api-gestao-financeira](https://github.com/sergiodiogoti/api-gestao-financeira)

---

### ⚙️ Passo a passo para execução

1. **Suba o microserviço de transacao onde contém o endpoint que será consumido pelo outro serviço:**
   ```bash
   mvn spring-boot:run

- Ao rodar a aplicação é possivel observar o log que demostra que foi criado um loader para logar as conversões de moeda de reais para USD e EURO
  ![cambio](docs/log-cambio-conversao-moedas.png)

- Porta padrão: 8080
- Endpoint principal:
GET http://localhost:8080/api/cambio/conversor-moedas/{saldo}
 
- Ao chamar o endpoint acima é feito uma requisição atraves do FeignClient para uma api-externa:
GET https://economia.awesomeapi.com.br/json/last/USD-BRL,EUR-BRL

- Exemplo de resposta:
```json

{
  "USDBRL": {
    "code": "USD",
    "codein": "BRL",
    "name": "Dólar Americano/Real Brasileiro",
    "high": "5.3601",
    "low": "5.31771",
    "varBid": "-0.0046",
    "pctChange": "-0.086172",
    "bid": "5.3335",
    "ask": "5.3435",
    "timestamp": "1759526951",
    "create_date": "2025-10-03 18:29:11"
  },
  "EURBRL": {
    "code": "EUR",
    "codein": "BRL",
    "name": "Euro/Real Brasileiro",
    "high": "6.28931",
    "low": "6.2422",
    "varBid": "0.011741",
    "pctChange": "0.187856",
    "bid": "6.26174",
    "ask": "6.27746",
    "timestamp": "1759525494",
    "create_date": "2025-10-03 18:04:54"
  }
}
```
Apos a resposta da awesomeapi, o serviço de transacoes cria uma regra de negocio para converter o saldo da conta em Reais para USD e EURO, e o retorno da aplicação fica assim:
![cambio](docs/print-api-transacoes-cambio-serviço-novo.png)

2. **Suba o projeto de Gestão Financeira (disciplina anterior):**
   ```bash
   mvn spring-boot:run
- Porta definida: 8081

- Endpoint de consumo:
  GET http://localhost:8081/api/transacoes/cambio/conversor-moedas/{saldo}
  ![cambio](docs/print-api-gestao-financeira-serviço-anterior.png)

3. **Teste a comunicação:**
- Primeiro, chame o microserviço de transaçoes diretamente (8080).
- Depois, chame o projeto de gestão (8081) e veja que ele retorna as mesmas informações, mas agora integradas ao seu domínio.

4. **Ilustração do Fluxo:**
 ![cambio](docs/ilustracao_fluxo.png)
