package br.ufal.ic.p2.myfood.exceptions;

public class EmailInvalidoException extends RuntimeException {
    public EmailInvalidoException() {
        super("Email invalido");
    }
}
