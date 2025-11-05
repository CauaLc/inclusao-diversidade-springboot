package br.com.fiap.inclusao_diversidade.repository;

import br.com.fiap.inclusao_diversidade.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepository extends JpaRepository <Colaborador, Long> {
    UserDetails findByEmail(String email);
}
