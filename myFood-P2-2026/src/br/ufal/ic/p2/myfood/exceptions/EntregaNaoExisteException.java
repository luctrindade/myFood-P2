package br.ufal.ic.p2.myfood.exceptions;

public class EntregaNaoExisteException extends RuntimeException {
    public EntregaNaoExisteException() {
        super("Nao existe entrega com esse id");
    }
}
