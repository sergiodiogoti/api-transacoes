package br.edu.infinet.sergiotransacoesapi.model.domain.exceptions;

public class ResourceInvalidException extends RuntimeException {

    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public ResourceInvalidException(String mensagem) {
        super(mensagem);
    }
}

