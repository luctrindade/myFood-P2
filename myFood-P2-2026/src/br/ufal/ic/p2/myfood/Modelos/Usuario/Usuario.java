package br.ufal.ic.p2.myfood.Modelos.Usuario;

import java.util.UUID;

public class Usuario {
    private int id;
    private String nome;
    private String endereco;
    private String senha;
    private String email;

    public Usuario(){

    }
    public Usuario(int id, String nome, String email, String senha, String endereco){
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.endereco = endereco;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
