package br.com.fiap.inclusao_diversidade.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

// DTO para criar/atualizar um Relatorio
public record RelatorioDeDiversidadeRequestDTO(
        @NotNull
        @PastOrPresent
        LocalDate dataGerada,

        @PositiveOrZero
        Integer totalColaborador,

        @PositiveOrZero
        Integer contagemDeMulheres,

        @PositiveOrZero
        Integer contagemDePessoasNegras,

        @PositiveOrZero
        Integer contagemDePessoasLgbt,

        @PositiveOrZero
        Integer contagemDePessoasComDesabilidade
) {}