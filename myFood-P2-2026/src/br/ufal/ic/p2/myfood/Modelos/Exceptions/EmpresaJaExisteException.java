package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class EmpresaJaExisteException extends RuntimeException {
    public EmpresaJaExisteException() {
        super("Empresa com esse nome ja existe");
    }
}
