package br.ufal.ic.p2.myfood.exceptions;

public class ValorInvalidoException extends RuntimeException {
    public ValorInvalidoException() {
        super("Valor invalido");
    }
}
