package br.ufal.ic.p2.myfood.exceptions;

public class TipoEmpresaInvalidoException extends RuntimeException {
    public TipoEmpresaInvalidoException() {
        super("Tipo de empresa invalido");
    }
}
