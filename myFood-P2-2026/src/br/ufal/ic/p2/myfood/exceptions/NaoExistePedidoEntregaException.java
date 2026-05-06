package br.ufal.ic.p2.myfood.exceptions;

public class NaoExistePedidoEntregaException extends RuntimeException {
    public NaoExistePedidoEntregaException() {
        super("Nao existe pedido para entrega");
    }
}
