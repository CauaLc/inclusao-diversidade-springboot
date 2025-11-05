package br.com.fiap.inclusao_diversidade.DTO;

import br.com.fiap.inclusao_diversidade.model.Treinamento;

import java.time.LocalDate;

// DTO para exibir um Treinamento
public record TreinamentoResponseDTO(
        Long id,
        String titulo,
        String descricao,
        LocalDate data
) {
    // Construtor que converte a Entidade Treinamento para este DTO
    public TreinamentoResponseDTO(Treinamento entity) {
        this(
                entity.getId(),
                entity.getTitulo(),
                entity.getDescricao(),
                entity.getData()
        );
    }
}