package br.com.fiap.inclusao_diversidade.model;


import jakarta.persistence.*;
// import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "tbl_colaboradores")
public class Colaborador {

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

    @Column (name = "treinamento_completo")
    private Boolean treinamentoCompleto = false;

    // --- Getters e Setters ---

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
}