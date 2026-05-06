package br.ufal.ic.p2.myfood.exceptions;

public class HorarioInvalidoException extends RuntimeException {
    public HorarioInvalidoException() {
        super("Horario invalido");
    }
}
