package br.ufal.ic.p2.myfood.Modelos.Exceptions;

public class EmpresaMesmoLocalException extends RuntimeException {
    public EmpresaMesmoLocalException() {
        super("Proibido cadastrar duas empresas com o mesmo nome e local");
    }
}
