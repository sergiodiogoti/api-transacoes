package br.edu.infinet.app.dto.request;

import br.edu.infinet.app.domain.enums.Instituicao;
import br.edu.infinet.app.domain.enums.TipoConta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ContaRequestDTO(

        @NotBlank(message = "O nome da conta é obrigatório.")
        String nome,

        @NotNull(message = "O tipo de conta é obrigatório.")
        TipoConta tipo,

        @NotNull(message = "A instituição é obrigatória.")
        Instituicao instituicao,

        @NotNull(message = "O saldo não pode ser nulo.")
        @Positive(message = "O saldo deve ser maior que zero.")
        Double saldo,

        @NotNull(message = "É necessário informar se a conta é principal.")
        Boolean principal,

        @NotNull(message = "O ID do usuário é obrigatório.")
        Integer usuarioId
) {}
