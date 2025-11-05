package br.com.fiap.inclusao_diversidade.DTO;

import br.com.fiap.inclusao_diversidade.model.Colaborador;
import br.com.fiap.inclusao_diversidade.model.ColaboradoresRole;

public record ColaboradorResponseDTO(
        Long id,
        String nomeColaborador,
        String generoColaborador,
        String etniaColaborador,
        Boolean temDisabilidade,
        String departamento,
        String email,
        ColaboradoresRole role,
        Boolean treinamentoCompleto
) {
    public ColaboradorResponseDTO(Colaborador entity) {
        this(
                entity.getId(),
                entity.getNomeColaborador(),
                entity.getGeneroColaborador(),
                entity.getEtniaColaborador(),
                entity.getTemDisabilidade(),
                entity.getDepartamento(),
                entity.getEmail(),
                entity.getRole(),
                entity.getTreinamentoCompleto()
        );
    }
}
