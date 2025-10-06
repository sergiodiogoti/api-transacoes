package br.edu.infinet.app.domain.model;

import jakarta.persistence.*;


@Entity
public class Usuario{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private String email;

	@Column(unique = true, nullable = false)
	private String cpf;

	private String telefone;

	private String senha;

	private double rendaMensal;

	private boolean ativo;

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


	public double getRendaMensal() {
		return rendaMensal;
	}

	public void setRendaMensal(double rendaMensal) {
		this.rendaMensal = rendaMensal;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Usuario{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", email='" + email + '\'' +
				", cpf='" + cpf + '\'' +
				", telefone='" + telefone + '\'' +
				", rendaMensal=" + rendaMensal +
				", ativo=" + ativo +
				'}';
	}
}
