package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.Modelos.Empresa.EmpresaManager;
import br.ufal.ic.p2.myfood.Modelos.Usuario.UsuarioManager;
import br.ufal.ic.p2.myfood.Modelos.Exceptions.*;

public class Facade {
    private UsuarioManager usuarioManager;
    private EmpresaManager empresaManager;

    public Facade(){
        this.usuarioManager = new UsuarioManager();
        this.empresaManager = new EmpresaManager(this.usuarioManager);
    }

    public void zerarSistema(){
        usuarioManager.zerarDados();
        empresaManager.zerarDados();
    }
    public void encerrarSistema(){
        usuarioManager.salvarDados();
        empresaManager.salvarDados();
    }

    public void criarUsuario(String nome, String email, String senha, String endereco) throws Exception{
        usuarioManager.criarUsuario(nome,email,senha,endereco);
    }
    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws Exception{
        usuarioManager.criarUsuario(nome,email,senha,endereco,cpf);
    }

    public int login(String email, String senha) throws Exception{
        return usuarioManager.login(email,senha);
    }

    public String getAtributoUsuario(int id, String atributo) throws Exception{
        return usuarioManager.getAtributoUsuario(id,atributo);
    }

    public int criarEmpresa(String tipoEmpresa, int dono, String nome, String endereco, String tipoCozinha) throws Exception{
        return empresaManager.criarEmpresas(tipoEmpresa,dono,nome,endereco,tipoCozinha);
    }

    public String getEmpresasDoUsuario(int donoId) throws Exception{
        return empresaManager.getEmpresaDoUsuario(donoId);
    }

    public int getIdEmpresa(int donoId, String nome, int indice) throws Exception{
        return empresaManager.getEmpresaId(donoId,nome,indice);
    }

    public String getAtributoEmpresa(int id, String atributo) throws Exception{
        return empresaManager.getAtributoEmpresa(id,atributo);
    }
}
