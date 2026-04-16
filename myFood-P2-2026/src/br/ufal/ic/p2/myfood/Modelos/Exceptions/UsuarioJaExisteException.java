package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class UsuarioJaExisteException extends Exception {
    public UsuarioJaExisteException() {
        super("Conta com esse email ja existe");
    }
}
