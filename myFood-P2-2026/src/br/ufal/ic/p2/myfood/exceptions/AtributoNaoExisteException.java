package br.ufal.ic.p2.myfood.exceptions;

public class AtributoNaoExisteException extends RuntimeException {
    public AtributoNaoExisteException() {
        super("Atributo nao existe");
    }
}
