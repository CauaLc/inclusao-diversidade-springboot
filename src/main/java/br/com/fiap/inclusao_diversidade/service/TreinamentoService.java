package br.com.fiap.inclusao_diversidade.service;

import br.com.fiap.inclusao_diversidade.DTO.TreinamentoRequestDTO;
import br.com.fiap.inclusao_diversidade.DTO.TreinamentoResponseDTO;
import br.com.fiap.inclusao_diversidade.model.Treinamento;
import br.com.fiap.inclusao_diversidade.repository.TreinamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TreinamentoService {

    @Autowired
    private final TreinamentoRepository treinamentoRepository;

    public TreinamentoService(TreinamentoRepository treinamentoRepository) {
        this.treinamentoRepository = treinamentoRepository;
    }

    @Transactional(readOnly = true)
    public List<TreinamentoResponseDTO> listarTodos() {
        return treinamentoRepository.findAll()
                .stream()
                .map(TreinamentoResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<TreinamentoResponseDTO> buscarPorId(Long id) {
        return treinamentoRepository.findById(id)
                .map(TreinamentoResponseDTO::new);
    }

    @Transactional
    public TreinamentoResponseDTO salvar(TreinamentoRequestDTO dto) {
        Treinamento entity = new Treinamento();
        mapDtoToEntity(dto, entity);
        Treinamento salvo = treinamentoRepository.save(entity);
        return new TreinamentoResponseDTO(salvo);
    }

    @Transactional
    public Optional<TreinamentoResponseDTO> atualizar(Long id, TreinamentoRequestDTO dto) {
        return treinamentoRepository.findById(id)
                .map(entityExistente -> {
                    mapDtoToEntity(dto, entityExistente);
                    Treinamento atualizado = treinamentoRepository.save(entityExistente);
                    return new TreinamentoResponseDTO(atualizado);
                });
    }

    public void deletar(Long id) {
        if (treinamentoRepository.existsById(id)) {
            treinamentoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Treinamento não encontrado para o id :: " + id);
        }
    }

    private void mapDtoToEntity(TreinamentoRequestDTO dto, Treinamento entity) {
        entity.setTitulo(dto.titulo());
        entity.setDescricao(dto.descricao());
        entity.setData(dto.data());
    }
}