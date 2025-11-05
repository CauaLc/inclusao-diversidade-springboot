package br.com.fiap.inclusao_diversidade.DTO;

import br.com.fiap.inclusao_diversidade.model.RelatorioDeDiversidade;

import java.time.LocalDate;

// DTO para exibir um Relatorio
public record RelatorioDeDiversidadeResponseDTO(
        Long id,
        LocalDate dataGerada,
        Integer totalColaborador,
        Integer contagemDeMulheres,
        Integer contagemDePessoasNegras,
        Integer contagemDePessoasLgbt,
        Integer contagemDePessoasComDesabilidade
) {
    // Construtor que converte a Entidade RelatorioDeDiversidade para este DTO
    public RelatorioDeDiversidadeResponseDTO(RelatorioDeDiversidade entity) {
        this(
                entity.getId(),
                entity.getDataGerada(),
                entity.getTotalColaborador(),
                entity.getContagemDeMulheres(),
                entity.getContagemDePessoasNegras(),
                entity.getContagemDePessoasLgbt(),
                entity.getContagemDePessoasComDesabilidade()
        );
    }
}