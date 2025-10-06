package br.edu.infinet.app.domain.enums;

public enum TipoConta {
    CORRENTE("Conta Corrente"),
    POUPANCA("Conta Poupança"),
    CARTEIRA("Carteira Física"),
    DIGITAL("Carteira Digital");

    private final String descricao;

    TipoConta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
