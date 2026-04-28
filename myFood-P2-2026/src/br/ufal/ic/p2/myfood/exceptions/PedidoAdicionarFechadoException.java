package br.ufal.ic.p2.myfood.exceptions;

public class PedidoAdicionarFechadoException extends RuntimeException {
    public PedidoAdicionarFechadoException() {
        super("Nao e possivel adcionar produtos a um pedido fechado");
    }
}
