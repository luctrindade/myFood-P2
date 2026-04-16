package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class EmailInvalidoException extends RuntimeException {
    public EmailInvalidoException() {
        super("Email invalido");
    }
}
