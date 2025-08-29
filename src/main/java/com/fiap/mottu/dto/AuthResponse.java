package com.fiap.mottu.dto;

public class AuthResponse {

    private String token;
    private String tipo = "Bearer";
    private String nome;
    private String email;
    private String perfil;

    // Construtores
    public AuthResponse() {}

    public AuthResponse(String token, String nome, String email, String perfil) {
        this.token = token;
        this.nome = nome;
        this.email = email;
        this.perfil = perfil;
    }

    // Getters e Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
