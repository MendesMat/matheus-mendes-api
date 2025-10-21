package br.edu.infnet.matheus_mendes_api.excecoes;

public class ExcecaoRecursoNaoEncontrado extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ExcecaoRecursoNaoEncontrado(String mensagem) {
        super(mensagem);
    }

    public ExcecaoRecursoNaoEncontrado(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
