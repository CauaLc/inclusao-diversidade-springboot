package br.com.fiap.inclusao_diversidade.dto;

import br.com.fiap.inclusao_diversidade.model.ParticipacaoEmTreinamento;

import java.time.LocalDate;

// DTO para exibir uma Participacao
public record ParticipacaoEmTreinamentoResponseDTO(
        Long id,
        ColaboradorResponseDTO colaborador,
        TreinamentoResponseDTO treinamento,
        Boolean completo,
        LocalDate dataDeConclusao
) {
    public ParticipacaoEmTreinamentoResponseDTO(ParticipacaoEmTreinamento entity) {
        this(
                entity.getId(),
                new ColaboradorResponseDTO(entity.getColaborador()),
                new TreinamentoResponseDTO(entity.getTreinamento()),
                entity.getCompleto(),
                entity.getDataDeConclusao()
        );
    }
}