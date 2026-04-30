package br.ufal.ic.p2.myfood.modelos.Empresa;

public class Farmacia extends Empresa {
    private Boolean aberto24;
    private int numeroFuncionarios;

    public Farmacia(){

    }

    public Farmacia(int id, int donoId, String nome, String endereco, Boolean aberto24, int numeroFuncionarios){
        super(id,donoId,nome,endereco,"farmacia");
        this.aberto24 = aberto24;
        this.numeroFuncionarios = numeroFuncionarios;
    }

    public Boolean getAberto24() {
        return aberto24;
    }

    public void setAberto24(Boolean aberto24) {
        this.aberto24 = aberto24;
    }

    public int getNumeroFuncionarios() {
        return numeroFuncionarios;
    }

    public void setNumeroFuncionarios(int numeroFuncionarios) {
        this.numeroFuncionarios = numeroFuncionarios;
    }
}
