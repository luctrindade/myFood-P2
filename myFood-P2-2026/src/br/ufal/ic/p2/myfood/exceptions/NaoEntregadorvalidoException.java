package br.ufal.ic.p2.myfood.exceptions;

public class NaoEntregadorvalidoException extends RuntimeException {
    public NaoEntregadorvalidoException() {
        super("Nao e um entregador valido");
    }
}
