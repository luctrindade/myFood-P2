package br.ufal.ic.p2.myfood.exceptions;

public class CategoriaInvalidaException extends RuntimeException {
    public CategoriaInvalidaException() {
        super("Categoria invalido");
    }
}
