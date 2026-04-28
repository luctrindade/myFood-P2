package br.ufal.ic.p2.myfood.exceptions;

public class ProdutoNaoPertenceException extends RuntimeException {
    public ProdutoNaoPertenceException() {
        super("O produto nao pertence a essa empresa");
    }
}
