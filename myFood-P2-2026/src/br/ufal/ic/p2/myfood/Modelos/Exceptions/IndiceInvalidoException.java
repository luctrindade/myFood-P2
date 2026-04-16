package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class IndiceInvalidoException extends RuntimeException {
    public IndiceInvalidoException() {
        super("Indice invalido");
    }
}
