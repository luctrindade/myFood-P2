package br.ufal.ic.p2.myfood.exceptions;

public class EntregadorNaoEmpresaException extends RuntimeException {
    public EntregadorNaoEmpresaException() {
        super("Entregador nao estar em nenhuma empresa.");
    }
}
