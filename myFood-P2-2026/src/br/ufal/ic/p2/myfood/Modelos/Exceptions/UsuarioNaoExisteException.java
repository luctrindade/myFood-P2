package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class UsuarioNaoExisteException extends RuntimeException {
    public UsuarioNaoExisteException() {
        super("Usuario nao cadastrado.");
    }
}
