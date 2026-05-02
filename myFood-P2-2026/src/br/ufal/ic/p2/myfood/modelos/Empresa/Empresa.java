package br.ufal.ic.p2.myfood.modelos.Empresa;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private int id;
    private int donoId;
    private String nome;
    private String endereco;
    private String tipoEmpresa;
    private List<Integer> entregadores;
    public Empresa(){
        this.entregadores = new ArrayList<>();
    }

    public Empresa(int id, int donoId, String nome, String endereco, String tipoEmpresa){
        this.id = id;
        this.donoId = donoId;
        this.nome = nome;
        this.endereco = endereco;
        this.tipoEmpresa = tipoEmpresa;
        this.entregadores = new ArrayList<>();
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

    public int getDonoId() {
        return donoId;
    }

    public void setDonoId(int donoId) {
        this.donoId = donoId;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    public List<Integer> getEntregadores() {
        return entregadores;
    }

    public void setEntregadores(List<Integer> entregadores) {
        this.entregadores = entregadores;
    }

    public void adicionarEntregador(int entregadorId){
        this.entregadores.add(entregadorId);
    }
}
