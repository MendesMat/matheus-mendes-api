package br.edu.infnet.matheus_mendes_api.excecoes;

public class ExcecaoRecursoDuplicado extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ExcecaoRecursoDuplicado(String mensagem) {
        super(mensagem);
    }
}