package br.ufal.ic.p2.myfood.exceptions;

public class EmpresaMesmoLocalException extends RuntimeException {
    public EmpresaMesmoLocalException() {
        super("Proibido cadastrar duas empresas com o mesmo nome e local");
    }
}
