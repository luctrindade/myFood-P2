package br.ufal.ic.p2.myfood.exceptions;

public class TipoMercadoInvalidoException extends RuntimeException {
    public TipoMercadoInvalidoException() {
        super("Tipo de mercado invalido");
    }
}
