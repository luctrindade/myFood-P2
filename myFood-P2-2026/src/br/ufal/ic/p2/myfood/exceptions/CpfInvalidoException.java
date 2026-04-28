package br.ufal.ic.p2.myfood.exceptions;

public class CpfInvalidoException extends RuntimeException {
    public CpfInvalidoException() {
        super("CPF invalido");
    }
}
