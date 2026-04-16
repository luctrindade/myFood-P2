package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class AtributoInvalidoException extends RuntimeException {
    public AtributoInvalidoException() {
        super("Atributo invalido");
    }
}
