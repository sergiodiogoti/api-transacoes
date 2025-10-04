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
