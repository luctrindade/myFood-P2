package br.ufal.ic.p2.myfood.exceptions;

public class NaoEUmMercadoValidoException extends RuntimeException {
    public NaoEUmMercadoValidoException() {
        super("Nao e um mercado valido");
    }
}
