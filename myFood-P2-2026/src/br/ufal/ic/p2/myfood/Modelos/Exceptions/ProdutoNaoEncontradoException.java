package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException() {
        super("Produto nao encontrado");
    }
}
