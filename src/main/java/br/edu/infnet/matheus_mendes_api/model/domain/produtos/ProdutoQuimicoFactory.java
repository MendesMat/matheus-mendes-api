package br.edu.infnet.matheus_mendes_api.model.domain.produtos;

import java.time.LocalDate;

import br.edu.infnet.matheus_mendes_api.model.domain.enums.Diluente;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.FormaFarmaceutica;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.PrincipioAtivo;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.TipoProduto;

public class ProdutoQuimicoFactory {
	public static ProdutoQuimicoBase criarProdutoPorTipo(ProdutoQuimicoBase produto) {
		return switch(produto.getTipoProduto()) {
		
			case INSETICIDA -> new Inseticida(produto.getFabricanteId(), produto.getTipoProduto(), produto.getNomeComercial(), 
					produto.getRegistroAnvisa(), produto.getValidadeRegistroAnvisa(), produto.getFormaFarmaceutica(), 
					produto.getPrincipioAtivo(), produto.getConcentracao(), produto.getDiluente());
			
			case RATICIDA -> new Raticida(produto.getFabricanteId(), produto.getTipoProduto(), produto.getNomeComercial(), 
					produto.getRegistroAnvisa(), produto.getValidadeRegistroAnvisa(), produto.getFormaFarmaceutica(), 
					produto.getPrincipioAtivo(), produto.getConcentracao(), produto.getDiluente());
			
			case DESINFETANTE -> new Desinfetante(produto.getFabricanteId(), produto.getTipoProduto(), produto.getNomeComercial(), 
					produto.getRegistroAnvisa(), produto.getValidadeRegistroAnvisa(), produto.getFormaFarmaceutica(), 
					produto.getPrincipioAtivo(), produto.getConcentracao(), produto.getDiluente());
			
			default -> throw new IllegalArgumentException("Tipo de produto inválido.");
		};
	}
	
	public static ProdutoQuimicoBase criarProdutoPorTipo(Integer fabricanteId, TipoProduto tipoProduto, String nomeComercial, 
			String registroAnvisa, LocalDate validadeRegistro, FormaFarmaceutica formaFarmaceutica, PrincipioAtivo principioAtivo,
			double concentracao, Diluente diluente) {
		return switch(tipoProduto) {
		
			case INSETICIDA -> new Inseticida(fabricanteId, tipoProduto, nomeComercial, registroAnvisa, validadeRegistro, formaFarmaceutica,
				principioAtivo, concentracao, diluente);
		
			case RATICIDA -> new Raticida(fabricanteId, tipoProduto, nomeComercial, registroAnvisa, validadeRegistro, formaFarmaceutica,
				principioAtivo, concentracao, diluente);
		
			case DESINFETANTE -> new Desinfetante(fabricanteId, tipoProduto, nomeComercial, registroAnvisa, validadeRegistro, formaFarmaceutica,
				principioAtivo, concentracao, diluente);
		
			default -> throw new IllegalArgumentException("Tipo de produto inválido.");
		};
	}		
}