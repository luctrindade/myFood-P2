package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.managers.*;
import br.ufal.ic.p2.myfood.exceptions.*;

public class Facade {
    private UsuarioManager usuarioManager;
    private EmpresaManager empresaManager;
    private ProdutoManager produtoManager;
    private PedidoManager pedidoManager;
    private EntregaManager entregaManager;
    public Facade(){
        this.usuarioManager = new UsuarioManager();
        this.empresaManager = new EmpresaManager(this.usuarioManager);
        this.produtoManager = new ProdutoManager(this.empresaManager);
        this.pedidoManager = new PedidoManager(this.usuarioManager,this.empresaManager, this.produtoManager);
        this.entregaManager = new EntregaManager(this.pedidoManager,this.empresaManager,this.usuarioManager);
    }

    public void zerarSistema(){
        usuarioManager.zerarDados();
        empresaManager.zerarDados();
        produtoManager.zerarDados();
        pedidoManager.zerarDados();
        entregaManager.zerarDados();
    }
    public void encerrarSistema(){
        usuarioManager.salvarDados();
        empresaManager.salvarDados();
        produtoManager.salvarDados();
        pedidoManager.salvarDados();
        entregaManager.salvarDados();
    }

    public void criarUsuario(String nome, String email, String senha, String endereco) throws Exception{
        usuarioManager.criarUsuario(nome,email,senha,endereco);
    }
    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws Exception{
        usuarioManager.criarUsuario(nome,email,senha,endereco,cpf);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String veiculo, String placa) throws Exception{
        usuarioManager.criarUsuario(nome,email,senha,endereco,veiculo,placa);
    }

    public void cadastrarEntregador(int empresa, int entregador) throws Exception{
        empresaManager.cadastrarEntregador(empresa,entregador);
    }

    public String getEntregadores(int empresa) throws Exception{
        return empresaManager.getEntregadores(empresa);
    }

    public String getEmpresas(int entregador) throws Exception{
        return empresaManager.getEmpresas(entregador);
    }

    public void liberarPedido(int numero) throws Exception{
        pedidoManager.liberarPedido(numero);
    }

    public int obterPedido(int entregador) throws Exception{
        return entregaManager.obterPedido(entregador);
    }

    public int criarEntrega(int pedido, int entregador, String destino) throws Exception{
        return entregaManager.criarEntrega(pedido,entregador,destino);
    }

    public String getEntrega(int id, String atributo) throws Exception{
        return entregaManager.getEntrega(id,atributo);
    }

    public int getIdEntrega(int id) throws Exception{
        return entregaManager.getIdEntrega(id);
    }

    public void entregar(int entrega) throws Exception{
        entregaManager.entregar(entrega);
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

    public int criarEmpresa(String tipoEmpresa, int dono, String nome, String endereco, Boolean aberto24, int numeroFuncionarios) throws Exception{
        return empresaManager.criarEmpresas(tipoEmpresa,dono,nome,endereco,aberto24,numeroFuncionarios);
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
