### Funcionalidade Escolhida
Foi adicionada a funcionalidade de **Transações Financeiras**, permitindo o registro de **receitas, despesas e transferências** no sistema de Gestão Financeira Pessoal.  
Essa funcionalidade se integra ao domínio existente, vinculando transações às **Contas** já cadastradas.

### Integração com o Projeto
- Cada transação está associada a uma **Conta** existente.
- As transações alteram o **saldo da conta** conforme o tipo:
   - Receita aumenta o saldo.
   - Despesa diminui o saldo, respeitando a regra de saldo suficiente.
   - Transferência movimenta valores entre contas de origem e destino.

### Principais Classes Implementadas
- **`Transacao`** (nova entidade)  
  Representa a movimentação financeira (id, valor, tipo, descrição, data, conta).
- **`TipoTransacao`** (enum)  
  Define os tipos possíveis de transação: `RECEITA`, `DESPESA`, `TRANSFERENCIA`.
- **`TransacaoService`** (classe de serviço)  
  Contém a lógica de negócio para validar e aplicar os efeitos das transações sobre as contas.

### Cenários de Teste Unitário
Foram criados testes unitários com JUnit 5, aplicando o ciclo **Red → Green → Refactor**.  
A seguir, os principais cenários:

1. **"Deve registrar uma receita e aumentar o saldo da conta"**
   - Objetivo: validar que receitas somam ao saldo existente.
   - Red → Teste criado antes do método, falhando até implementação mínima.

2. **"Deve registrar uma despesa e diminuir o saldo da conta"**
   - Objetivo: validar que despesas reduzem o saldo corretamente.

3. **"Não deve permitir despesa maior que o saldo da conta"**
   - Objetivo: garantir regra de negócio de saldo insuficiente.
   - Utiliza `assertThrows` para validar a exceção.

4. **"Deve transferir valores entre duas contas corretamente"**
   - Objetivo: validar movimentação simultânea em conta origem e destino.

5. **"Não deve permitir transação com valor negativo"**
   - Objetivo: reforçar validação de entrada com valores inválidos.

### Resultado
Todos os testes unitários foram implementados e estão em **estado GREEN**, comprovando a correta aplicação da metodologia **TDD (Test-Driven Development)**. 
