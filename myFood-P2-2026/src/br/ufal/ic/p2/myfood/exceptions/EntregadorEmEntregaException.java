package br.ufal.ic.p2.myfood.exceptions;

public class EntregadorEmEntregaException extends RuntimeException {
    public EntregadorEmEntregaException() {
        super("Entregador ainda em entrega");
    }
}
