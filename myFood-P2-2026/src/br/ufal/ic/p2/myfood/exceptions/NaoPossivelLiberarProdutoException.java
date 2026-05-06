package br.ufal.ic.p2.myfood.exceptions;

public class NaoPossivelLiberarProdutoException extends RuntimeException {
    public NaoPossivelLiberarProdutoException() {
        super("Nao e possivel liberar um produto que nao esta sendo preparado");
    }
}
