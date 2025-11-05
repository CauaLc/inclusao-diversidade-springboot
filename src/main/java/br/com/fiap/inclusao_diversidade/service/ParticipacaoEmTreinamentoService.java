package br.com.fiap.inclusao_diversidade.service;

import br.com.fiap.inclusao_diversidade.DTO.ParticipacaoEmTreinamentoRequestDTO;
import br.com.fiap.inclusao_diversidade.DTO.ParticipacaoEmTreinamentoResponseDTO;
import br.com.fiap.inclusao_diversidade.model.Colaborador;
import br.com.fiap.inclusao_diversidade.model.ParticipacaoEmTreinamento;
import br.com.fiap.inclusao_diversidade.model.Treinamento;
import br.com.fiap.inclusao_diversidade.repository.ColaboradorRepository;
import br.com.fiap.inclusao_diversidade.repository.ParticipacaoEmTreinamentoRepository;
import br.com.fiap.inclusao_diversidade.repository.TreinamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParticipacaoEmTreinamentoService {

    private final ParticipacaoEmTreinamentoRepository participacaoRepository;
    private final ColaboradorRepository colaboradorRepository;
    private final TreinamentoRepository treinamentoRepository;

    public ParticipacaoEmTreinamentoService(ParticipacaoEmTreinamentoRepository participacaoRepository,
                                            ColaboradorRepository colaboradorRepository,
                                            TreinamentoRepository treinamentoRepository) {
        this.participacaoRepository = participacaoRepository;
        this.colaboradorRepository = colaboradorRepository;
        this.treinamentoRepository = treinamentoRepository;
    }

    @Transactional(readOnly = true)
    public List<ParticipacaoEmTreinamentoResponseDTO> listarTodos() {
        return participacaoRepository.findAll()
                .stream()
                .map(ParticipacaoEmTreinamentoResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ParticipacaoEmTreinamentoResponseDTO> buscarPorId(Long id) {
        return participacaoRepository.findById(id)
                .map(ParticipacaoEmTreinamentoResponseDTO::new);
    }

    @Transactional
    public ParticipacaoEmTreinamentoResponseDTO salvar(ParticipacaoEmTreinamentoRequestDTO dto) {
        ParticipacaoEmTreinamento entity = new ParticipacaoEmTreinamento();
        mapDtoToEntity(dto, entity);
        ParticipacaoEmTreinamento salvo = participacaoRepository.save(entity);
        return new ParticipacaoEmTreinamentoResponseDTO(salvo);
    }

    @Transactional
    public Optional<ParticipacaoEmTreinamentoResponseDTO> atualizar(Long id, ParticipacaoEmTreinamentoRequestDTO dto) {
        return participacaoRepository.findById(id)
                .map(entityExistente -> {
                    mapDtoToEntity(dto, entityExistente);
                    ParticipacaoEmTreinamento salvo = participacaoRepository.save(entityExistente);
                    return new ParticipacaoEmTreinamentoResponseDTO(salvo);
                });
    }


    public void deletar(Long id) {
        if (participacaoRepository.existsById(id)) {
            participacaoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Participação não encontrada para o id :: " + id);
        }
    }

    // Método utilitário complexo
    private void mapDtoToEntity(ParticipacaoEmTreinamentoRequestDTO dto, ParticipacaoEmTreinamento entity) {
        // Busca o Colaborador pelo ID ou lança excecao
        Colaborador colaborador = colaboradorRepository.findById(dto.colaboradorId())
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado id :: " + dto.colaboradorId()));

        // Busca o Treinamento pelo ID ou lança excecao
        Treinamento treinamento = treinamentoRepository.findById(dto.treinamentoId())
                .orElseThrow(() -> new RuntimeException("Treinamento não encontrado id :: " + dto.treinamentoId()));

        entity.setColaborador(colaborador);
        entity.setTreinamento(treinamento);
        entity.setCompleto(dto.completo());
        entity.setDataDeConclusao(dto.dataDeConclusao());
    }
}