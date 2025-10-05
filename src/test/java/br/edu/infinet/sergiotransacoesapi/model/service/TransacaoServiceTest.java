package br.edu.infinet.sergiotransacoesapi.model.service;

import br.edu.infinet.sergiotransacoesapi.model.domain.Conta;
import br.edu.infinet.sergiotransacoesapi.model.domain.Transacao;
import br.edu.infinet.sergiotransacoesapi.model.domain.enums.TipoTransacao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransacaoServiceTest {

    @Test
    @DisplayName("Deve registrar uma receita e aumentar o saldo da conta")
    void deveRegistrarReceitaEAumentarSaldo() {
        Conta conta = new Conta();
        conta.setSaldo(100.0);

        Transacao receita = new Transacao();
        receita.setValor(50.0);
        receita.setTipo(TipoTransacao.RECEITA);

        TransacaoService service = new TransacaoService();
        service.registrarTransacao(conta, receita);

        assertEquals(150.0, conta.getSaldo());
    }

    @Test
    @DisplayName("Não deve permitir transação com valor negativo")
    void naoDevePermitirValorNegativo() {
        Conta conta = new Conta();
        conta.setSaldo(100.0);

        Transacao receita = new Transacao();
        receita.setValor(-50.0);
        receita.setTipo(TipoTransacao.RECEITA);

        TransacaoService service = new TransacaoService();

        assertThrows(IllegalArgumentException.class,
                () -> service.registrarTransacao(conta, receita));
    }

        @Test
        @DisplayName("Deve registrar uma despesa e diminuir o saldo da conta")
        void deveRegistrarDespesaEDiminuirSaldo() {
            Conta conta = new Conta();
            conta.setSaldo(200.0);

            Transacao despesa = new Transacao();
            despesa.setValor(80.0);
            despesa.setTipo(TipoTransacao.DESPESA);

            TransacaoService service = new TransacaoService();
            service.registrarTransacao(conta, despesa);


            assertEquals(120.0, conta.getSaldo());
        }

        @Test
        @DisplayName("Não deve permitir despesa maior que o saldo da conta")
        void naoDevePermitirDespesaMaiorQueSaldo() {
            Conta conta = new Conta();
            conta.setSaldo(100.0);

            Transacao despesa = new Transacao();
            despesa.setValor(200.0);
            despesa.setTipo(TipoTransacao.DESPESA);

            TransacaoService service = new TransacaoService();

            assertThrows(IllegalArgumentException.class,
                    () -> service.registrarTransacao(conta, despesa));

        }

    @Test
    @DisplayName("Deve transferir valores entre duas contas corretamente")
    void deveTransferirEntreContas() {
        Conta origem = new Conta();
        origem.setSaldo(300.0);

        Conta destino = new Conta();
        destino.setSaldo(100.0);

        Transacao transferencia = new Transacao();
        transferencia.setValor(150.0);
        transferencia.setTipo(TipoTransacao.TRANSFERENCIA);

        TransacaoService service = new TransacaoService();
        service.transferir(origem, destino, transferencia);

        assertEquals(150.0, origem.getSaldo());
        assertEquals(250.0, destino.getSaldo());
    }
}