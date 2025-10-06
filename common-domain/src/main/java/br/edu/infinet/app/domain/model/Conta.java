package br.edu.infinet.app.domain.model;

 import br.edu.infinet.app.domain.enums.Instituicao;
 import br.edu.infinet.app.domain.enums.TipoConta;
 import jakarta.persistence.*;

 @Entity
 public class Conta {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;

     private String nome;

     @Enumerated(EnumType.STRING)
     private TipoConta tipo;

     @Enumerated(EnumType.STRING)
     private Instituicao instituicao;

     private Double saldo;

     private boolean principal;

     @ManyToOne
     @JoinColumn(name = "usuario_id", nullable = false)
     private Usuario usuario;

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

     public boolean isPrincipal() {
         return principal;
     }

     public void setPrincipal(boolean principal) {
         this.principal = principal;
     }

     public Usuario getUsuario() {
         return usuario;
     }

     public void setUsuario(Usuario usuario) {
         this.usuario = usuario;
     }

     @Override
     public String toString() {
         return super.toString() + String.format(" - %s - Tipo de Conta: %s - Saldo: %.2f - Principal: %s ",
                 nome, tipo, saldo, principal ? "Sim" : "Não");
     }
 }