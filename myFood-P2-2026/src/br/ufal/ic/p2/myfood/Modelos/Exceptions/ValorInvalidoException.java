package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class ValorInvalidoException extends RuntimeException {
    public ValorInvalidoException() {
        super("Valor invalido");
    }
}
