package br.com.fiap.inclusao_diversidade.dto;

import br.com.fiap.inclusao_diversidade.model.Colaborador;

// DTO para exibir um Colaborador
public record ColaboradorResponseDTO(
        Long id,
        String nomeColaborador,
        String generoColaborador,
        String etniaColaborador,
        Boolean temDisabilidade,
        String departamento,
        Boolean treinamentoCompleto
) {
    // Construtor que converte a Entidade Colaborador para este DTO
    public ColaboradorResponseDTO(Colaborador entity) {
        this(
                entity.getId(),
                entity.getNomeColaborador(),
                entity.getGeneroColaborador(),
                entity.getEtniaColaborador(),
                entity.getTemDisabilidade(),
                entity.getDepartamento(),
                entity.getTreinamentoCompleto()
        );
    }
}