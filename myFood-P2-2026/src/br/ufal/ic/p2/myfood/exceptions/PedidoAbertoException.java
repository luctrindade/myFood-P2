package br.ufal.ic.p2.myfood.exceptions;

public class PedidoAbertoException extends RuntimeException {
    public PedidoAbertoException() {
        super("Nao existe pedido em aberto");
    }
}
