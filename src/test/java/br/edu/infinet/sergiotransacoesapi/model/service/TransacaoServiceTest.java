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
        receita.setValor(-50.0); // Valor inválido
        receita.setTipo(TipoTransacao.RECEITA);

        TransacaoService service = new TransacaoService();

        // Espera-se que lance IllegalArgumentException
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

            // O teste espera que o saldo seja 120.0
            assertEquals(120.0, conta.getSaldo());
        }

        @Test
        @DisplayName("Não deve permitir despesa maior que o saldo da conta")
        void naoDevePermitirDespesaMaiorQueSaldo() {
            Conta conta = new Conta();
            conta.setSaldo(100.0);

            Transacao despesa = new Transacao();
            despesa.setValor(200.0); // Valor maior que o saldo
            despesa.setTipo(TipoTransacao.DESPESA);

            TransacaoService service = new TransacaoService();

            // Espera-se que lance IllegalArgumentException
            assertThrows(IllegalArgumentException.class,
                    () -> service.registrarTransacao(conta, despesa));

        }
}