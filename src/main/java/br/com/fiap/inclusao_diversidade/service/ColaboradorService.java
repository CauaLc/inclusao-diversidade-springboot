package br.com.fiap.inclusao_diversidade.service;

import br.com.fiap.inclusao_diversidade.dto.ColaboradorRequestDTO;
import br.com.fiap.inclusao_diversidade.dto.ColaboradorResponseDTO;
import br.com.fiap.inclusao_diversidade.model.Colaborador;
import br.com.fiap.inclusao_diversidade.repository.ColaboradorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;

    public ColaboradorService(ColaboradorRepository colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    @Transactional(readOnly = true)
    public List<ColaboradorResponseDTO> listarTodos() {
        return colaboradorRepository.findAll()
                .stream()
                .map(ColaboradorResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ColaboradorResponseDTO> buscarPorId(Long id) {
        return colaboradorRepository.findById(id)
                .map(ColaboradorResponseDTO::new);
    }

    @Transactional
    public ColaboradorResponseDTO salvar(ColaboradorRequestDTO dto) {
        Colaborador entity = new Colaborador();
        mapDtoToEntity(dto, entity);
        Colaborador salvo = colaboradorRepository.save(entity);
        return new ColaboradorResponseDTO(salvo);
    }

    @Transactional
    public Optional<ColaboradorResponseDTO> atualizar(Long id, ColaboradorRequestDTO dto) {
        return colaboradorRepository.findById(id)
                .map(entityExistente -> {
                    mapDtoToEntity(dto, entityExistente);
                    Colaborador atualizado = colaboradorRepository.save(entityExistente);
                    return new ColaboradorResponseDTO(atualizado);
                });
    }

    public void deletar(Long id) {
        // Adicionando verificacao de existencia para evitar erros
        if (colaboradorRepository.existsById(id)) {
            colaboradorRepository.deleteById(id);
        } else {
            // Lancar uma exceção ou tratar caso não encontre
            throw new RuntimeException("Colaborador não encontrado para o id :: " + id); // Exemplo
        }
    }

    public Colaborador salvarUsuario(Colaborador colaborador){

        String senhaCriptografada = new
                BCryptPasswordEncoder().encode(colaborador.getPassword());

        Colaborador usuario = new Colaborador();
        BeanUtils.copyProperties(colaborador, usuario);
        usuario.setSenha(senhaCriptografada);

        Colaborador usuarioSalvo = colaboradorRepository.save(usuario);

        return  usuarioSalvo;

    }

}

    // Método utilitário para mapear DTO para Entidade
    private void mapDtoToEntity(ColaboradorRequestDTO dto, Colaborador entity) {
        entity.setNomeColaborador(dto.nomeColaborador());
        entity.setGeneroColaborador(dto.generoColaborador());
        entity.setEtniaColaborador(dto.etniaColaborador());
        entity.setTemDisabilidade(dto.temDisabilidade());
        entity.setDepartamento(dto.departamento());
        entity.setTreinamentoCompleto(dto.treinamentoCompleto());
    }
}