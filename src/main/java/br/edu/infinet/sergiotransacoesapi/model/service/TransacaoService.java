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
            default -> throw new UnsupportedOperationException("Lógica não implementada.");
        }
    }

    private void validarValor(Transacao transacao) {
        if (transacao.getValor() == null || transacao.getValor() <= 0) {
            throw new IllegalArgumentException("O valor da transação deve ser positivo");
        }
    }
}