package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class EmpresaNaoExisteException extends RuntimeException {
    public EmpresaNaoExisteException() {
        super("Nao existe empresa com esse nome");
    }
}
