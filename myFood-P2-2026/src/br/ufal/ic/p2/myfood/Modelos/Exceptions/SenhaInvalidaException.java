package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException() {
        super("Senha invalido");
    }
}
