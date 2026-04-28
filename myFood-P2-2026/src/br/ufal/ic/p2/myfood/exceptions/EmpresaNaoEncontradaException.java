package br.ufal.ic.p2.myfood.exceptions;

public class EmpresaNaoEncontradaException extends RuntimeException {
    public EmpresaNaoEncontradaException() {
        super("Empresa nao encontrada");
    }
}
