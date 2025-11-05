package br.com.fiap.inclusao_diversidade.service;

import br.com.fiap.inclusao_diversidade.model.Colaborador;
import br.com.fiap.inclusao_diversidade.repository.ColaboradorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;

    public ColaboradorService(ColaboradorRepository colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    public List<Colaborador> listarTodos() {
        return colaboradorRepository.findAll();
    }

    public Optional<Colaborador> buscarPorId(Long id) {
        return colaboradorRepository.findById(id);
    }

    public Colaborador salvar(Colaborador colaborador) {
        return colaboradorRepository.save(colaborador);
    }

    public void deletar(Long id) {
        colaboradorRepository.deleteById(id);
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

