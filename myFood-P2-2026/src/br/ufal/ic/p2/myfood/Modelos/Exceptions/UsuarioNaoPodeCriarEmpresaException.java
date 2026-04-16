package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class UsuarioNaoPodeCriarEmpresaException extends RuntimeException {
    public UsuarioNaoPodeCriarEmpresaException() {
        super("Usuario nao pode criar uma empresa");
    }
}
