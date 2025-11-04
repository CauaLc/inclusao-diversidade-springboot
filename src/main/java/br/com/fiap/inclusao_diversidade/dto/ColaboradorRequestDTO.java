package br.com.fiap.inclusao_diversidade.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// DTO para criar ou atualizar um Colaborador
public record ColaboradorRequestDTO(
        @NotBlank(message = "O nome do contato é obrigatório")
        String nomeColaborador,

        String generoColaborador,

        String etniaColaborador,

        @NotNull(message = "O campo 'temDisabilidade' é obrigatório")
        Boolean temDisabilidade,

        String departamento,

        @NotNull(message = "O campo 'treinamentoCompleto' é obrigatório")
        Boolean treinamentoCompleto
) {}