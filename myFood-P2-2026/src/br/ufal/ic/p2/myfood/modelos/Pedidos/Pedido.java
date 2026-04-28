package br.ufal.ic.p2.myfood.modelos.Pedidos;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int numero;
    private int clienteId;
    private int empresaId;
    private String estado;
    private List<Integer> produtosId;

    public Pedido(){

    }

    public Pedido(int numero, int clienteId, int empresaId){
        this.numero = numero;
        this.clienteId = clienteId;
        this.empresaId = empresaId;
        this.estado = "aberto";
        this.produtosId = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(int empresaId) {
        this.empresaId = empresaId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Integer> getProdutosId() {
        return produtosId;
    }

    public void setProdutosId(List<Integer> produtosId) {
        this.produtosId = produtosId;
    }
}
