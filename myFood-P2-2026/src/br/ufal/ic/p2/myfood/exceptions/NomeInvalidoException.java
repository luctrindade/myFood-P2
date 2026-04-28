package br.ufal.ic.p2.myfood.exceptions;

public class NomeInvalidoException extends RuntimeException {
    public NomeInvalidoException() {
        super("Nome invalido");
    }
}
