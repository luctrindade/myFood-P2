package br.ufal.ic.p2.myfood.exceptions;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException() {
        super("Produto nao encontrado");
    }
}
