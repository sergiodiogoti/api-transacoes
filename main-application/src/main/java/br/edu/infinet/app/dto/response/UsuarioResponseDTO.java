package br.edu.infinet.app.dto.response;

public class UsuarioResponseDTO {
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private Double rendaMensal;

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(String nome, String email, String cpf, String telefone, Double rendaMensal) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.rendaMensal = rendaMensal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Double getRendaMensal() {
        return rendaMensal;
    }

    public void setRendaMensal(Double rendaMensal) {
        this.rendaMensal = rendaMensal;
    }

    @Override
    public String toString() {
        return "{" +
                "nome:'" + nome + '\'' +
                ", email:'" + email + '\'' +
                ", cpf:'" + cpf + '\'' +
                ", telefone:'" + telefone + '\'' +
                ", rendaMensal:" + rendaMensal +
                '}';
    }
}

