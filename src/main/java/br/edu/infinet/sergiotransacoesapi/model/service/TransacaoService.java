package br.edu.infinet.sergiotransacoesapi.model.service;

import br.edu.infinet.sergiotransacoesapi.model.domain.Conta;
import br.edu.infinet.sergiotransacoesapi.model.domain.Transacao;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

    public void registrarTransacao(Conta conta, Transacao transacao) {
        validarValor(transacao);

        switch (transacao.getTipo()) {
            case RECEITA -> conta.setSaldo(conta.getSaldo() + transacao.getValor());
            case DESPESA -> registrarDespesa(conta, transacao);
            default -> throw new UnsupportedOperationException("Use o método transferir para transações de transferência");
        }
    }

    public void transferir(Conta origem, Conta destino, Transacao transacao) {
        validarValor(transacao);

        if (origem.getSaldo() < transacao.getValor()) {
            throw new IllegalArgumentException("Saldo insuficiente para transferência");
        }

        origem.setSaldo(origem.getSaldo() - transacao.getValor());
        destino.setSaldo(destino.getSaldo() + transacao.getValor());
    }

    private void registrarDespesa(Conta conta, Transacao transacao) {
        if (conta.getSaldo() < transacao.getValor()) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        conta.setSaldo(conta.getSaldo() - transacao.getValor());
    }

    private void validarValor(Transacao transacao) {
        if (transacao.getValor() == null || transacao.getValor() <= 0) {
            throw new IllegalArgumentException("O valor da transação deve ser positivo");
        }
    }
}
