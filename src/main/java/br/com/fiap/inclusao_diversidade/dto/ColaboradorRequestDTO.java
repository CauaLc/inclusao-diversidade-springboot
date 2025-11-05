package br.com.fiap.inclusao_diversidade.DTO;

import br.com.fiap.inclusao_diversidade.model.ColaboradoresRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ColaboradorRequestDTO(

        @NotBlank(message = "O nome do colaborador é obrigatório.")
        String nomeColaborador,

        String generoColaborador,

        String etniaColaborador,

        @NotNull(message = "O campo 'temDisabilidade' é obrigatório.")
        Boolean temDisabilidade,

        String departamento,

        @Email(message = "O e-mail deve ser válido.")
        @NotBlank(message = "O e-mail é obrigatório.")
        String email,

        @NotBlank(message = "A senha é obrigatória.")
        String senha,

        @NotNull(message = "O cargo (role) é obrigatório.")
        ColaboradoresRole role,

        // O campo pode ser opcional (não exigido na criação)
        Boolean treinamentoCompleto
) {}
