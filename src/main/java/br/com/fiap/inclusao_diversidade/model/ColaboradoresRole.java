package br.com.fiap.inclusao_diversidade.model;

public enum ColaboradoresRole {
    ADMIN("admin"),
    USER("user");

    private String role;

    ColaboradoresRole(String role){
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }
}
