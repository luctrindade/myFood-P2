package br.ufal.ic.p2.myfood.Modelos.Empresa;

public class Restaurante extends Empresa{
    private String tipoCozinha;

    public Restaurante(){

    }
    public Restaurante(int id, int donoId, String nome, String endereco, String tipoCozinha){
        super(id,donoId,nome,endereco,"Restaurante");
        this.tipoCozinha = tipoCozinha;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }

    public void setTipoCozinha(String tipoCozinha) {
        this.tipoCozinha = tipoCozinha;
    }
}
