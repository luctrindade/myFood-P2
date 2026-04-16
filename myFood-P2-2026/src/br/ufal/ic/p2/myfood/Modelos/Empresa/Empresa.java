package br.ufal.ic.p2.myfood.Modelos.Empresa;

public class Empresa {
    private int id;
    private int donoId;
    private String name;
    private String endereco;
    private String tipoEmpresa;

    public Empresa(){

    }

    public Empresa(int id, int donoId, String name, String endereco, String tipoEmpresa){
        this.id = id;
        this.donoId = donoId;
        this.name = name;
        this.endereco = endereco;
        this.tipoEmpresa = tipoEmpresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
