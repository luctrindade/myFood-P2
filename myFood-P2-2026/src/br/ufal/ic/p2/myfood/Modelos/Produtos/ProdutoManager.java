package br.ufal.ic.p2.myfood.Modelos.Produtos;

import br.ufal.ic.p2.myfood.Modelos.Empresa.Empresa;
import br.ufal.ic.p2.myfood.Modelos.Empresa.EmpresaManager;
import br.ufal.ic.p2.myfood.Modelos.Exceptions.*;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProdutoManager {
    private List<Produto> produtoList;
    private int proximoId;
    private EmpresaManager empresaManager;

    public ProdutoManager(EmpresaManager empresaManager){
        this.empresaManager = empresaManager;
        carregarDados();
    }

    @SuppressWarnings("unchecked")
    private void carregarDados(){
        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream("produtos.xml"))){
            this.produtoList = (List<Produto>) decoder.readObject();
            this.proximoId = (int) decoder.readObject();
        } catch (Exception e){
            this.produtoList = new ArrayList<>();
            this.proximoId = 1;
        }
    }

    public void zerarDados(){
        this.produtoList.clear();
        this.proximoId = 1;
        File arquivo = new File("produtos.xml");
        if(arquivo.exists()) arquivo.delete();
    }

    public void salvarDados(){
        try(XMLEncoder encoder = new XMLEncoder(new FileOutputStream("produtos.xml"))){
            encoder.writeObject(this.produtoList);
            encoder.writeObject(this.proximoId);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void validarProdutos(String nome, float valor, String categoria) throws Exception{
        if(nome == null || nome.isBlank()) throw new NomeInvalidoException();
        if(valor < 0) throw new ValorInvalidoException();
        if(categoria == null || categoria.isBlank()) throw new CategoriaInvalidaException();
    }

    public int criarProduto(int empresa, String nome, float valor, String categoria) throws Exception{
        validarProdutos(nome,valor,categoria);

        for(Produto p : produtoList){
            if(p.getEmpresaId() == empresa && p.getNome().equals(nome)){
                throw new ProdutoJaExisteException();
            }
        }

        int fId = proximoId;
        produtoList.add(new Produto(fId,empresa,nome,valor,categoria));
        proximoId++;
        return fId;
    }

    public void editarProduto(int produtoId, String nome, float valor, String categoria) throws Exception{
        validarProdutos(nome,valor,categoria);

        Produto findProduto = null;
        for(Produto p : produtoList){
            if(p.getId() == produtoId){
                findProduto = p;
                break;
            }
        }

        if (findProduto == null){
            throw new ProdutoNaoCadastradoException();
        }

        findProduto.setNome(nome);
        findProduto.setCategoria(categoria);
        findProduto.setValor(valor);
    }

    public String getProduto(String nome, int empresa, String atributo) throws Exception{
        Produto findProduto = null;
        for(Produto p : produtoList){
            if(p.getEmpresaId() == empresa && p.getNome().equals(nome)){
                findProduto = p;
                break;
            }
        }
        if(findProduto == null) throw new ProdutoNaoEncontradoException();

        switch(atributo){
            case "nome":
                return findProduto.getNome();
            case "valor":
                return String.format(Locale.US, "%.2f", findProduto.getValor());
            case "categoria":
                return findProduto.getCategoria();
            case "empresa":
                return empresaManager.getAtributoEmpresa(empresa, "nome");
            default:
                throw new AtributoNaoExisteException();
        }
    }

    public String listarProdutos(int empresa) throws Exception{
        try{
            empresaManager.getAtributoEmpresa(empresa, "nome");
        } catch (Exception e){
            throw new EmpresaNaoEncontradaException();
        }

        StringBuilder sb = new StringBuilder("{[");
        boolean first = true;

        for(Produto p : produtoList){
            if(p.getEmpresaId() == empresa){
                if(!first)
                    sb.append(", ");
                sb.append(p.getNome());
                first = false;
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}
