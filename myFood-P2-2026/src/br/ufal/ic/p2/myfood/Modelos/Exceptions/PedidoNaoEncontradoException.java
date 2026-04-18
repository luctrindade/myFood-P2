package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class PedidoNaoEncontradoException extends RuntimeException {
    public PedidoNaoEncontradoException() {
        super("Pedido nao encontrado");
    }
}
