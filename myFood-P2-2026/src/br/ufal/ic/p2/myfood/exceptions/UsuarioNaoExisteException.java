package br.ufal.ic.p2.myfood.exceptions;

public class UsuarioNaoExisteException extends RuntimeException {
    public UsuarioNaoExisteException() {
        super("Usuario nao cadastrado.");
    }
}
