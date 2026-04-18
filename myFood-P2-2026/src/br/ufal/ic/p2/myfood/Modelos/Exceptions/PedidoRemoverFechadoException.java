package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class PedidoRemoverFechadoException extends RuntimeException {
    public PedidoRemoverFechadoException() {
        super("Nao e possivel remover produtos de um pedido fechado");
    }
}
