package br.ufal.ic.p2.myfood.exceptions;

public class UsuarioNaoPodeCriarEmpresaException extends RuntimeException {
    public UsuarioNaoPodeCriarEmpresaException() {
        super("Usuario nao pode criar uma empresa");
    }
}
