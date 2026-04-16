package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class CpfInvalidoException extends RuntimeException {
    public CpfInvalidoException() {
        super("CPF invalido");
    }
}
