package br.ufal.ic.p2.myfood.exceptions;

public class EnderecoInvalidoException extends RuntimeException {
    public EnderecoInvalidoException() {
        super("Endereco invalido");
    }
}
