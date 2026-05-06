package br.ufal.ic.p2.myfood.exceptions;

public class PedidoNaoProntoEntregaException extends RuntimeException {
    public PedidoNaoProntoEntregaException() {
        super("Pedido nao esta pronto para entrega");
    }
}
