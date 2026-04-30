package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.managers.EmpresaManager;
import br.ufal.ic.p2.myfood.managers.PedidoManager;
import br.ufal.ic.p2.myfood.managers.ProdutoManager;
import br.ufal.ic.p2.myfood.managers.UsuarioManager;
import br.ufal.ic.p2.myfood.exceptions.*;

public class Facade {
    private UsuarioManager usuarioManager;
    private EmpresaManager empresaManager;
    private ProdutoManager produtoManager;
    private PedidoManager pedidoManager;

    public Facade(){
        this.usuarioManager = new UsuarioManager();
        this.empresaManager = new EmpresaManager(this.usuarioManager);
        this.produtoManager = new ProdutoManager(this.empresaManager);
        this.pedidoManager = new PedidoManager(this.usuarioManager,this.empresaManager, this.produtoManager);
    }

    public void zerarSistema(){
        usuarioManager.zerarDados();
        empresaManager.zerarDados();
        produtoManager.zerarDados();
        pedidoManager.zerarDados();
    }
    public void encerrarSistema(){
        usuarioManager.salvarDados();
        empresaManager.salvarDados();
        produtoManager.salvarDados();
        pedidoManager.salvarDados();
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

    public int criarEmpresa(String tipoEmpresa, int dono, String nome, String endereco, String abre, String fecha, String tipoMercado) throws Exception{
        return empresaManager.criarEmpresas(tipoEmpresa,dono,nome,endereco,abre,fecha,tipoMercado);
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

    public int criarProduto(int empresa, String nome, float valor, String categoria) throws Exception{
        return produtoManager.criarProduto(empresa,nome,valor,categoria);
    }

    public void editarProduto(int produto, String nome, float valor, String categoria) throws Exception{
         produtoManager.editarProduto(produto,nome,valor,categoria);
    }

    public String getProduto(String nome, int empresa, String atributo) throws Exception{
        return produtoManager.getProduto(nome,empresa,atributo);
    }

    public String listarProdutos(int empresa) throws Exception{
        return produtoManager.listarProdutos(empresa);
    }

    public int criarPedido(int cliente, int empresa) throws Exception{
        return pedidoManager.criarPedido(cliente, empresa);
    }

    public int getNumeroPedido(int cliente, int empresa, int indice) throws Exception{
        return pedidoManager.getNumeroPedido(cliente,empresa,indice);
    }
    public void adicionarProduto(int numero, int produto) throws Exception{
        try{
            pedidoManager.adicionarProduto(numero,produto);
        }
        catch (Exception e){
            if(e.getMessage().equals("Pedido nao encontrado")) throw new PedidoAbertoException();
            throw e;
        }
    }
    public String getPedidos(int numero, String atributo) throws Exception{
        return pedidoManager.getPedidos(numero,atributo);
    }

    public void fecharPedido(int numero) throws  Exception{
        pedidoManager.fecharPedido(numero);
    }
    public void removerProduto(int pedido, String produto) throws Exception{
        pedidoManager.removerProduto(pedido,produto);
    }

    public void alterarFuncionamento(int mercado, String abre, String fecha) throws Exception{
        empresaManager.alterarFuncionamento(mercado,abre,fecha);
    }
}
