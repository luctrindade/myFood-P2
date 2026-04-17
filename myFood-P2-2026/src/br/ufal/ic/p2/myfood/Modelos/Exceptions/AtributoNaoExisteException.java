package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class AtributoNaoExisteException extends RuntimeException {
    public AtributoNaoExisteException() {
        super("Atributo nao existe");
    }
}
