package br.ufal.ic.p2.myfood.exceptions;

public class NadaParaEntregueException extends RuntimeException {
    public NadaParaEntregueException() {
        super("Nao existe nada para ser entregue com esse id");
    }
}
