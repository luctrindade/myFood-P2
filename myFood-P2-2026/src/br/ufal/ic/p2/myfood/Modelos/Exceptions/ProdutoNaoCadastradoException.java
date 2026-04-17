package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class ProdutoNaoCadastradoException extends RuntimeException {
    public ProdutoNaoCadastradoException() {
        super("Produto nao cadastrado");
    }
}
