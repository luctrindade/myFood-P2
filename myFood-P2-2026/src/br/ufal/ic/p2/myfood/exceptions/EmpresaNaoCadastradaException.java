package br.ufal.ic.p2.myfood.exceptions;

public class EmpresaNaoCadastradaException extends RuntimeException {
    public EmpresaNaoCadastradaException() {
        super("Empresa nao cadastrada");
    }
}
