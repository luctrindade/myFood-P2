package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class EnderecoInvalidoException extends RuntimeException {
    public EnderecoInvalidoException() {
        super("Endereco invalido");
    }
}
