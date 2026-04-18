package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class ProdutoInvalidoException extends RuntimeException {
    public ProdutoInvalidoException() {
        super("Produto invalido");
    }
}
