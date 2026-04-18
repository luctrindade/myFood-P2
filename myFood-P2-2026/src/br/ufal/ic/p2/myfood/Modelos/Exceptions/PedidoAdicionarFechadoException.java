package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class PedidoAdicionarFechadoException extends RuntimeException {
    public PedidoAdicionarFechadoException() {
        super("Nao e possivel adcionar produtos a um pedido fechado");
    }
}
