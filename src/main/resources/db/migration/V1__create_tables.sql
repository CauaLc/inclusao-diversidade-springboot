

-- MIGRATION V1 - Criação das tabelas iniciais

-- ====== SEQUENCES ======
CREATE SEQUENCE SEQ_COLABORADOR START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SEQ_TREINAMENTO START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SEQ_PARTICIPACAO_TREINAMENTO START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE SEQ_RELATORIO_DIVERSIDADE START WITH 1 INCREMENT BY 1;

-- TABELA: TBL_COLABORADORES
CREATE TABLE tbl_colaboradores (
    id NUMBER(19) PRIMARY KEY,
    nome_colaborador VARCHAR2(255) NOT NULL,
    genero_colaborador VARCHAR2(100),
    etnia_colaborador VARCHAR2(100),
    tem_deficiencia NUMBER(1),
    departamento VARCHAR2(100),
    treinamento_completo NUMBER(1) DEFAULT 0
);

-- ====== TABELA: TBL_TREINAMENTO
CREATE TABLE tbl_treinamento (
    id NUMBER(19) PRIMARY KEY,
    titulo VARCHAR2(255) NOT NULL,
    descricao VARCHAR2(1000),
    data_treinamento DATE
);

-- TABELA: TBL_PARTICIPACAO_TREINAMENTO
CREATE TABLE tbl_participacao_treinamento (
    id NUMBER(19) PRIMARY KEY,
    colaborador_id NUMBER(19),
    treinamento_id NUMBER(19),
    completo NUMBER(1),
    data_conclusao DATE,
    CONSTRAINT fk_colaborador FOREIGN KEY (colaborador_id)
        REFERENCES tbl_colaboradores (id),
    CONSTRAINT fk_treinamento FOREIGN KEY (treinamento_id)
        REFERENCES tbl_treinamento (id)
);

-- TABELA: TBL_RELATORIO_DIVERSIDADE
CREATE TABLE tbl_relatorio_diversidade (
    id NUMBER(19) PRIMARY KEY,
    data_gerada DATE,
    total_colaborador NUMBER(10),
    contagem_mulheres NUMBER(10),
    contagem_pessoas_negras NUMBER(10),
    contagem_pessoas_lgbt NUMBER(10),
    contagem_pessoas_desabilidades NUMBER(10)
);