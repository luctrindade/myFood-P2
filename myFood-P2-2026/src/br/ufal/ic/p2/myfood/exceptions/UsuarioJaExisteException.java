package br.ufal.ic.p2.myfood.exceptions;

public class UsuarioJaExisteException extends Exception {
    public UsuarioJaExisteException() {
        super("Conta com esse email ja existe");
    }
}
