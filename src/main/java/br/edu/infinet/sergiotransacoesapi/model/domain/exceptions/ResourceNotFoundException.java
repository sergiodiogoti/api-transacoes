package br.edu.infinet.sergiotransacoesapi.model.domain.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public ResourceNotFoundException(String mensagem) {
        super(mensagem);
    }
}

