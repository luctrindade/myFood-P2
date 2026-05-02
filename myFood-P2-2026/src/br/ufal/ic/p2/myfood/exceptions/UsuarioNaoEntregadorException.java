package br.ufal.ic.p2.myfood.exceptions;

public class UsuarioNaoEntregadorException extends RuntimeException {
    public UsuarioNaoEntregadorException() {
        super("Usuario nao e um entregador");
    }
}
