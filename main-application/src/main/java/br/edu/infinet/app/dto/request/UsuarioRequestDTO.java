package br.edu.infinet.app.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO (

    @NotBlank(message = "O nome é obrigatório")
    String nome,

    @Email(message = "O e-mail informado é inválido")
    @NotBlank(message = "O e-mail é obrigatório")
    String email,

    @NotBlank(message = "O CPF é obrigatório")
    String cpf,

    @NotBlank(message = "A Senha é obrigatória")
    String senha,

    @NotBlank(message = "O telefone é obrigatório")
    String telefone,

    Double rendaMensal

    ){}



