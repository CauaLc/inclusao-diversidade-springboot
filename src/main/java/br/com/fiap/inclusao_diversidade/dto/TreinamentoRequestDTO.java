package br.com.fiap.inclusao_diversidade.DTO;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

// DTO para criar ou atualizar um Treinamento
public record TreinamentoRequestDTO(
        @NotBlank(message = "O titulo é obrigatório")
        String titulo,

        String descricao,

        @FutureOrPresent(message = "A data deve ser no presente ou futuro")
        LocalDate data
) {}