package br.ufal.ic.p2.myfood.exceptions;

public class ProdutoInvalidoException extends RuntimeException {
    public ProdutoInvalidoException() {
        super("Produto invalido");
    }
}
