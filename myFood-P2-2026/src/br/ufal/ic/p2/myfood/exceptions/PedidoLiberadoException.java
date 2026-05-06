package br.ufal.ic.p2.myfood.exceptions;

public class PedidoLiberadoException extends RuntimeException {
    public PedidoLiberadoException() {
        super("Pedido ja liberado");
    }
}
