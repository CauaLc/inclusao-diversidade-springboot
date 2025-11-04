package br.com.fiap.inclusao_diversidade.service;

import br.com.fiap.inclusao_diversidade.model.Treinamento;
import br.com.fiap.inclusao_diversidade.repository.TreinamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreinamentoService {

    @Autowired
    private final TreinamentoRepository treinamentoRepository;

    public TreinamentoService(TreinamentoRepository treinamentoRepository) {
        this.treinamentoRepository = treinamentoRepository;
    }

    public List<Treinamento> listarTodos() {
        return treinamentoRepository.findAll();
    }

    public Optional<Treinamento> buscarPorId(Long id) {
        return treinamentoRepository.findById(id);
    }

    public Treinamento salvar(Treinamento treinamento) {
        return treinamentoRepository.save(treinamento);
    }

    public void deletar(Long id) {
        treinamentoRepository.deleteById(id);
    }
}

