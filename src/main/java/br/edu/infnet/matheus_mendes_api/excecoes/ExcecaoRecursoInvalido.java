package br.edu.infnet.matheus_mendes_api.excecoes;

public class ExcecaoRecursoInvalido extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ExcecaoRecursoInvalido(String mensagem) {
        super(mensagem);
    }

    public ExcecaoRecursoInvalido(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
