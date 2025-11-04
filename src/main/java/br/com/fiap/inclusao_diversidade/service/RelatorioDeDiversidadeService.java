package br.com.fiap.inclusao_diversidade.service;

import br.com.fiap.inclusao_diversidade.dto.RelatorioDeDiversidadeRequestDTO;
import br.com.fiap.inclusao_diversidade.dto.RelatorioDeDiversidadeResponseDTO;
import br.com.fiap.inclusao_diversidade.model.RelatorioDeDiversidade;
import br.com.fiap.inclusao_diversidade.repository.RelatorioDeDiversidadeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RelatorioDeDiversidadeService {

    private final RelatorioDeDiversidadeRepository relatorioRepository;

    public RelatorioDeDiversidadeService(RelatorioDeDiversidadeRepository relatorioRepository) {
        this.relatorioRepository = relatorioRepository;
    }

    @Transactional(readOnly = true)
    public List<RelatorioDeDiversidadeResponseDTO> listarTodos() {
        return relatorioRepository.findAll()
                .stream()
                .map(RelatorioDeDiversidadeResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<RelatorioDeDiversidadeResponseDTO> buscarPorId(Long id) {
        return relatorioRepository.findById(id)
                .map(RelatorioDeDiversidadeResponseDTO::new);
    }

    @Transactional
    public RelatorioDeDiversidadeResponseDTO salvar(RelatorioDeDiversidadeRequestDTO dto) {
        RelatorioDeDiversidade entity = new RelatorioDeDiversidade();
        mapDtoToEntity(dto, entity);
        RelatorioDeDiversidade salvo = relatorioRepository.save(entity);
        return new RelatorioDeDiversidadeResponseDTO(salvo);
    }

    @Transactional
    public Optional<RelatorioDeDiversidadeResponseDTO> atualizar(Long id, RelatorioDeDiversidadeRequestDTO dto) {
        return relatorioRepository.findById(id)
                .map(entityExistente -> {
                    mapDtoToEntity(dto, entityExistente);
                    RelatorioDeDiversidade salvo = relatorioRepository.save(entityExistente);
                    return new RelatorioDeDiversidadeResponseDTO(salvo);
                });
    }

    public void deletar(Long id) {
        if (relatorioRepository.existsById(id)) {
            relatorioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Relatório não encontrado para o id :: " + id);
        }
    }

    private void mapDtoToEntity(RelatorioDeDiversidadeRequestDTO dto, RelatorioDeDiversidade entity) {
        entity.setDataGerada(dto.dataGerada());
        entity.setTotalColaborador(dto.totalColaborador());
        entity.setContagemDeMulheres(dto.contagemDeMulheres());
        entity.setContagemDePessoasNegras(dto.contagemDePessoasNegras());
        entity.setContagemDePessoasLgbt(dto.contagemDePessoasLgbt());
        entity.setContagemDePessoasComDesabilidade(dto.contagemDePessoasComDesabilidade());
    }
}