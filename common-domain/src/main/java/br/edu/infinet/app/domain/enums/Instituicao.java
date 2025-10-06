package br.edu.infinet.app.domain.enums;

public enum Instituicao {
    BRADESCO("Bradesco"),
    ITAU("Itaú"),
    CAIXA("Caixa Econômica Federal"),
    SANTANDER("Santander"),
    BANCO_DO_BRASIL("Banco do Brasil"),
    NUBANK("Nubank"),
    INTER("Banco Inter"),
    MERCADO_PAGO("Mercado Pago"),
    PIC_PAY("PicPay");

    private final String nome;

    Instituicao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
