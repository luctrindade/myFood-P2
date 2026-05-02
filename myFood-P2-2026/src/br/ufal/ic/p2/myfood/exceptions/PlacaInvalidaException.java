package br.ufal.ic.p2.myfood.exceptions;

public class PlacaInvalidaException extends RuntimeException {
    public PlacaInvalidaException() {
        super("Placa invalido");
    }
}
