package br.com.fiap.inclusao_diversidade.DTO;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

// DTO para criar/atualizar uma Participacao
public record ParticipacaoEmTreinamentoRequestDTO(
        @NotNull(message = "ID do colaborador é obrigatório")
        Long colaboradorId,

        @NotNull(message = "ID do treinamento é obrigatório")
        Long treinamentoId,

        @NotNull
        Boolean completo,

        LocalDate dataDeConclusao
) {}