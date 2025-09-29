## Nova Funcionalidade: Transação

### Por que escolhi essa funcionalidade?
A gestão financeira pessoal só faz sentido se o sistema permitir registrar receitas e despesas. 
Assim, implementei um módulo de **Transações**, que se integra com as **Contas** já existentes.

### Principais classes criadas
- **Transacao (modelo)**: representa uma movimentação financeira (receita, despesa, transferência).
- **TransacaoService (serviço)**: contém as regras de negócio, como atualizar saldo das contas e validar operações.

### Cenários de Teste (TDD)
1. **[DisplayName: "Deve registrar uma receita e aumentar o saldo da conta"]**  
   - Verifica se, ao registrar uma transação do tipo RECEITA, o saldo da conta é atualizado corretamente.  

2. **[DisplayName: "Deve registrar uma despesa e diminuir o saldo da conta"]**  
   - Valida que uma transação de DESPESA reduz o saldo.  

3. **[DisplayName: "Não deve permitir despesa maior que o saldo da conta"]**  
   - Garante que o sistema lança `SaldoInsuficienteException` caso o valor da despesa seja maior que o saldo disponível.  

4. **[DisplayName: "Não deve aceitar transação com valor negativo"]**  
   - Verifica que o sistema lança exceção para valores inválidos.  

5. **[DisplayName: "Deve transferir valores entre contas corretamente"]**  
   - Verifica que uma TRANSFERÊNCIA diminui o saldo da conta de origem e aumenta o saldo da conta destino.  
