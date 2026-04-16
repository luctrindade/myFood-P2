package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class IndiceMaiorQueEsperadoException extends RuntimeException {
    public IndiceMaiorQueEsperadoException() {
        super("Indice maior que o esperado");
    }
}
