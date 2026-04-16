package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class EmpresaNaoCadastradaException extends RuntimeException {
    public EmpresaNaoCadastradaException() {
        super("Empresa nao cadastrada");
    }
}
