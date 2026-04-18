package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class PedidoAbertoException extends RuntimeException {
    public PedidoAbertoException() {
        super("Nao existe pedido em aberto");
    }
}
