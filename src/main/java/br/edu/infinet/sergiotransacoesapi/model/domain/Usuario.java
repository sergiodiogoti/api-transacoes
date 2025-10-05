package br.edu.infinet.sergiotransacoesapi.model.domain;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario extends Pessoa {


	@Min(value = 0, message = "A pontuação de Credito deve ser um número positivo.")
	private int pontuacaoCredito;

	@NotNull(message = "A rendaMensal é obrigatória.")
	@Min(value = 1, message = "A rendaMensal deve ser um número positivo.")
	private double rendaMensal;

	@NotBlank(message = "O Perfil é obrigatório.")
	@Size(min=3, max=50)
	private String perfil;

	private boolean ativo;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id")
	@Valid
	private Endereco endereco;

	@JsonIgnore
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Conta> contas = new ArrayList<>();

	@Override
	public String obterTipo() {
		return "Usuário";
	}

	public int getPontuacaoCredito() {
		return pontuacaoCredito;
	}

	public void setPontuacaoCredito(int pontuacaoCredito) {
		this.pontuacaoCredito = pontuacaoCredito;
	}

	public double getRendaMensal() {
		return rendaMensal;
	}

	public void setRendaMensal(double rendaMensal) {
		this.rendaMensal = rendaMensal;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}

	@Override
	public String toString() {
		return super.toString() +
				String.format(" - Pontuação de Credito: %d - Renda Mensal: %.2f - Perfil: %s - Ativo: %s - Endereço: %s",
						pontuacaoCredito, rendaMensal, perfil, ativo ? "Sim" : "Não", endereco);
	}
}
