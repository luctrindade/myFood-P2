package br.ufal.ic.p2.myfood.exceptions;

public class EnderecoEmpresaInvalidoException extends RuntimeException {
    public EnderecoEmpresaInvalidoException() {
        super("Endereco da empresa invalido");
    }
}
