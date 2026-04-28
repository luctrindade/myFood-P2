package br.ufal.ic.p2.myfood.exceptions;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException() {
        super("Senha invalido");
    }
}
