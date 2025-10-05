package br.edu.infinet.sergiotransacoesapi.model.service;

import br.edu.infinet.sergiotransacoesapi.model.domain.Conta;
import br.edu.infinet.sergiotransacoesapi.model.domain.Transacao;
import br.edu.infinet.sergiotransacoesapi.model.domain.enums.TipoTransacao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransacaoServiceTest {

    @Test
    @DisplayName("Deve registrar uma receita e aumentar o saldo da conta")
    void deveRegistrarReceitaEAumentarSaldo() {
        Conta conta = new Conta();
        // Assumindo que Conta já possui o método setSaldo
        conta.setSaldo(100.0);

        Transacao receita = new Transacao();
        // Assumindo que Transacao já possui os métodos setValor e setTipo
        receita.setValor(50.0);
        receita.setTipo(TipoTransacao.RECEITA);

        TransacaoService service = new TransacaoService();
        service.registrarTransacao(conta, receita);

        // O teste espera que o saldo seja 150.0
        assertEquals(150.0, conta.getSaldo());
    }
}