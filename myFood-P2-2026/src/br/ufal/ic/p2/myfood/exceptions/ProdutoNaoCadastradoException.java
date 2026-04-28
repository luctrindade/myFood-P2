package br.ufal.ic.p2.myfood.exceptions;

public class ProdutoNaoCadastradoException extends RuntimeException {
    public ProdutoNaoCadastradoException() {
        super("Produto nao cadastrado");
    }
}
