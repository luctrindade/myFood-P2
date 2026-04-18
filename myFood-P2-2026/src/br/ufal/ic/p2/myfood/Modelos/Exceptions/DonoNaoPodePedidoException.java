package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class DonoNaoPodePedidoException extends RuntimeException {
    public DonoNaoPodePedidoException() {
        super("Dono de empresa nao pode fazer um pedido");
    }
}
