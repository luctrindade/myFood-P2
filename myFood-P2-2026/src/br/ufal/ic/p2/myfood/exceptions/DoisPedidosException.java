package br.ufal.ic.p2.myfood.exceptions;

public class DoisPedidosException extends RuntimeException {
    public DoisPedidosException() {
        super("Nao e permitido ter dois pedidos em aberto para a mesma empresa");
    }
}
