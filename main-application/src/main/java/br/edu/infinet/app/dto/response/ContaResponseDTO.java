package br.edu.infinet.app.dto.response;

import br.edu.infinet.app.domain.enums.Instituicao;
import br.edu.infinet.app.domain.enums.TipoConta;


public class ContaResponseDTO {

    private Integer id;
    private String nome;
    private TipoConta tipo;
    private Instituicao instituicao;
    private Double saldo;
    private Double saldoEURO;
    private Double saldoUSD;
    private Boolean principal;
    private Integer usuarioId;

    public ContaResponseDTO() {
    }

    public ContaResponseDTO(Integer id, String nome, TipoConta tipo, Instituicao instituicao,
                            Double saldo, Boolean principal, Integer usuarioId) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.instituicao = instituicao;
        this.saldo = saldo;
        this.principal = principal;
        this.usuarioId = usuarioId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoConta getTipo() {
        return tipo;
    }

    public void setTipo(TipoConta tipo) {
        this.tipo = tipo;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Double getSaldoEURO() {
        return saldoEURO;
    }

    public void setSaldoEURO(Double saldoEURO) {
        this.saldoEURO = saldoEURO;
    }

    public Double getSaldoUSD() {
        return saldoUSD;
    }

    public void setSaldoUSD(Double saldoUSD) {
        this.saldoUSD = saldoUSD;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", nome:'" + nome + '\'' +
                ", tipo:" + tipo +
                ", instituicao:" + instituicao +
                ", saldo:" + saldo +
                ", saldoEuro:" + saldoEURO +
                ", saldoUSd:" + saldoUSD +
                ", principal:" + principal +
                ", usuarioId:" + usuarioId +
                '}';
    }
}

