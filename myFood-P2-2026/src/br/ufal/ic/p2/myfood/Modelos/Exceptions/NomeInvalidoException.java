package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class NomeInvalidoException extends RuntimeException {
    public NomeInvalidoException() {
        super("Nome invalido");
    }
}
