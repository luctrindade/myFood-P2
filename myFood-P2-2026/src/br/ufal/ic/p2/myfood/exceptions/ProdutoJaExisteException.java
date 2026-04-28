package br.ufal.ic.p2.myfood.exceptions;

public class ProdutoJaExisteException extends RuntimeException {
    public ProdutoJaExisteException() {
        super("Ja existe um produto com esse nome para essa empresa");
    }
}
