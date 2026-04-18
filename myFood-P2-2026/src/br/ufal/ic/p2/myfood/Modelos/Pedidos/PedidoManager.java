package br.ufal.ic.p2.myfood.Modelos.Pedidos;

import br.ufal.ic.p2.myfood.Modelos.Empresa.EmpresaManager;
import br.ufal.ic.p2.myfood.Modelos.Exceptions.*;
import br.ufal.ic.p2.myfood.Modelos.Produtos.Produto;
import br.ufal.ic.p2.myfood.Modelos.Produtos.ProdutoManager;
import br.ufal.ic.p2.myfood.Modelos.Usuario.Dono;
import br.ufal.ic.p2.myfood.Modelos.Usuario.Usuario;
import br.ufal.ic.p2.myfood.Modelos.Usuario.UsuarioManager;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PedidoManager {
    private List<Pedido> pedidoList;
    private int  proximoNumero;

    private UsuarioManager usuarioManager;
    private EmpresaManager empresaManager;
    private ProdutoManager produtoManager;

    public PedidoManager(UsuarioManager usuarioManager, EmpresaManager empresaManager, ProdutoManager produtoManager){
        this.usuarioManager = usuarioManager;
        this.empresaManager = empresaManager;
        this.produtoManager = produtoManager;
        carregarDados();
    }

    @SuppressWarnings("unchecked")
    private void carregarDados(){
        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream("pedidos.xml"))){
            this.pedidoList = (List<Pedido>) decoder.readObject();
            this.proximoNumero= (int) decoder.readObject();
        } catch (Exception e){
            this.pedidoList = new ArrayList<>();
            this.proximoNumero = 1;
        }
    }

    public void zerarDados(){
        this.pedidoList.clear();
        this.proximoNumero = 1;
        File arquivo = new File("pedidos.xml");
        if(arquivo.exists()) arquivo.delete();
    }

    public void salvarDados(){
        try(XMLEncoder encoder = new XMLEncoder(new FileOutputStream("pedidos.xml"))){
            encoder.writeObject(this.pedidoList);
            encoder.writeObject(this.proximoNumero);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    public int criarPedido(int clienteId, int empresaId) throws Exception{
        Usuario usuario = usuarioManager.getUsuario(clienteId);
        if(usuario instanceof Dono){
            throw new DonoNaoPodePedidoException();
        }

        for(Pedido p : pedidoList){
            if(p.getClienteId() == clienteId && p.getEmpresaId() == empresaId
                    && p.getEstado().equals("aberto")){
                throw new DoisPedidosException();
            }
        }
        int fNumero = proximoNumero;
        pedidoList.add(new Pedido(fNumero,clienteId,empresaId));
        proximoNumero++;
        return fNumero;
    }

    public int getNumeroPedido(int clienteId, int empresaId, int indice) throws Exception{
        List<Pedido> filtro = new ArrayList<>();
        for(Pedido p : pedidoList){
            if(p.getClienteId() == clienteId && p.getEmpresaId() == empresaId){
                filtro.add(p);
            }
        }
        return filtro.get(indice).getNumero();
    }

    private Pedido getPedidoNumero(int numero) throws Exception{
        for(Pedido p : pedidoList){
            if(p.getNumero() == numero){
                return p;
            }
        }
        throw new PedidoNaoEncontradoException();
    }

    private String formataListaProduto(Pedido pedido) throws Exception{
        StringBuilder sb = new StringBuilder("{[");
        boolean first = true;
        for(Integer pId : pedido.getProdutosId()){
            Produto p = produtoManager.getProdutoById(pId);
            if(!first) sb.append(", ");
            sb.append(p.getNome());
            first = false;
        }
        sb.append("]}");
        return sb.toString();
    }

    private String calculaValor(Pedido pedido) throws Exception{
        float soma = 0;
        for(Integer pId : pedido.getProdutosId()){
            Produto p = produtoManager.getProdutoById(pId);
            soma += p.getValor();
        }
        return String.format(Locale.US, "%.2f", soma);
    }

    public void adicionarProduto(int numeroPedido, int produtoId) throws Exception{
        Pedido pedido = getPedidoNumero(numeroPedido);

        if(!pedido.getEstado().equals("aberto")){
            throw new PedidoAdicionarFechadoException();
        }

        Produto produto = produtoManager.getProdutoById(produtoId);

        if(produto.getEmpresaId() != pedido.getEmpresaId()){
            throw new ProdutoNaoPertenceException();
        }

        pedido.getProdutosId().add(produto.getId());
    }

    public void removerProduto(int numeroPedido, String nomeProduto) throws Exception{
        if(nomeProduto == null || nomeProduto.isBlank()) {
            throw new ProdutoInvalidoException();
        }
        Pedido pedido = getPedidoNumero(numeroPedido);
        if(!pedido.getEstado().equals("aberto")){
            throw new PedidoRemoverFechadoException();
        }
        Produto produtoRemove = null;
        try{
            produtoRemove = produtoManager.getProdutoPorNomeEmpresa(nomeProduto, pedido.getEmpresaId());
        } catch (Exception e){
            throw new ProdutoNaoEncontradoException();
        }

        boolean r = pedido.getProdutosId().remove((Integer) produtoRemove.getId());
        if(!r){
            throw new ProdutoNaoEncontradoException();
        }
    }

    public void fecharPedido(int numeroPedido) throws Exception{
        Pedido pedido = getPedidoNumero(numeroPedido);
        pedido.setEstado("preparando");
    }

    public String getPedidos(int numeroPedido, String atributo) throws Exception{
        if(atributo == null || atributo.isBlank()) {
            throw new AtributoInvalidoException();
        }
        Pedido pedido = getPedidoNumero(numeroPedido);
        switch (atributo){
            case "cliente":
                return usuarioManager.getUsuario(pedido.getClienteId()).getNome();
            case "empresa":
                return empresaManager.getAtributoEmpresa(pedido.getEmpresaId(), "nome");
            case "produtos":
                return formataListaProduto(pedido);
            case "estado":
                return pedido.getEstado();
            case "valor":
                return calculaValor(pedido);
            default:
                throw new AtributoNaoExisteException();
        }
    }
}
