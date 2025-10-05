 package br.edu.infinet.sergiotransacoesapi.model.domain;

 import br.edu.infinet.sergiotransacoesapi.model.domain.enums.Instituicao;
 import br.edu.infinet.sergiotransacoesapi.model.domain.enums.TipoConta;
 import jakarta.persistence.*;
 import jakarta.validation.Valid;
 import jakarta.validation.constraints.Min;
 import jakarta.validation.constraints.NotBlank;
 import jakarta.validation.constraints.NotNull;
 import jakarta.validation.constraints.Size;

 @Entity
 public class Conta {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;

     @NotBlank(message = "O nome é obrigatório.")
     @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
     private String nome;

     @NotNull(message = "O tipo da conta é obrigatório.")
     @Enumerated(EnumType.STRING)
     private TipoConta tipo;

     @NotNull(message = "A Instituição da conta é obrigatória.")
     @Enumerated(EnumType.STRING)
     private Instituicao instituicao;

     @NotNull(message = "O Saldo é obrigatório.")
     @Min(value = 1, message = "O Saldo deve ser um número positivo.")
     private Double saldo;

     private boolean principal;

     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "usuario_id", nullable = false)
     @Valid
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