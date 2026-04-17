package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class CategoriaInvalidaException extends RuntimeException {
    public CategoriaInvalidaException() {
        super("Categoria invalido");
    }
}
