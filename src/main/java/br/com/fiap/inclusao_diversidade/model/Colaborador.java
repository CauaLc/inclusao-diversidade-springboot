package br.com.fiap.inclusao_diversidade.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tbl_colaboradores")
public class Colaborador implements UserDetails {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_COLABORADOR"
    )

    @SequenceGenerator(
            name = "SEQ_COLABORADOR",
            sequenceName = "SEQ_COLABORADOR",
            allocationSize = 1
    )

    private Long id;

    @Column(name = "nome_colaborador")
    private String nomeColaborador;

    @Column(name = "genero_colaborador")
    private String generoColaborador;

    @Column(name = "etnia_colaborador")
    private String etniaColaborador;

    @Column (name = "tem_deficiencia")
    private Boolean temDisabilidade;

    private String departamento;

    private String email;

    @NotEmpty(message = "Uma senha deve ser cadastrada!")
    private String senha;

    @Enumerated(EnumType.STRING)
    private ColaboradoresRole role;

    @Column (name = "treinamento_completo")
    private Boolean treinamentoCompleto = false;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ColaboradoresRole getRole() {
        return role;
    }

    public void setRole(ColaboradoresRole role) {
        this.role = role;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeColaborador() {
        return nomeColaborador;
    }

    public void setNomeColaborador(String nomeColaborador) {
        this.nomeColaborador = nomeColaborador;
    }

    public String getGeneroColaborador() {
        return generoColaborador;
    }

    public void setGeneroColaborador(String generoColaborador) {
        this.generoColaborador = generoColaborador;
    }

    public String getEtniaColaborador() {
        return etniaColaborador;
    }

    public void setEtniaColaborador(String etniaColaborador) {
        this.etniaColaborador = etniaColaborador;
    }

    public Boolean getTemDisabilidade() {
        return temDisabilidade;
    }

    public void setTemDisabilidade(Boolean temDisabilidade) {
        this.temDisabilidade = temDisabilidade;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Boolean getTreinamentoCompleto() {
        return treinamentoCompleto;
    }

    public void setTreinamentoCompleto(Boolean treinamentoCompleto) {
        this.treinamentoCompleto = treinamentoCompleto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == ColaboradoresRole.ADMIN) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
