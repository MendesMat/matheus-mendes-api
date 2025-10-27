package br.edu.infnet.matheus_mendes_api.modelo.dominio.produtos;

import java.time.LocalDate;

import br.edu.infnet.matheus_mendes_api.modelo.dominio.Fabricante;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.enums.*;

public class ProdutoQuimicoFactory {	
	public static ProdutoQuimicoBase criarProdutoPorTipo(Fabricante fabricante, TipoProduto tipoProduto, String nomeComercial, 
			String registroAnvisa, LocalDate validadeRegistro, FormaFarmaceutica formaFarmaceutica, PrincipioAtivo principioAtivo,
			double concentracao, Diluente diluente) {
		return switch(tipoProduto) {
		
			case INSETICIDA -> new Inseticida(fabricante, tipoProduto, nomeComercial, registroAnvisa, validadeRegistro, formaFarmaceutica,
				principioAtivo, concentracao, diluente);
		
			case RATICIDA -> new Raticida(fabricante, tipoProduto, nomeComercial, registroAnvisa, validadeRegistro, formaFarmaceutica,
				principioAtivo, concentracao, diluente);
		
			case DESINFETANTE -> new Desinfetante(fabricante, tipoProduto, nomeComercial, registroAnvisa, validadeRegistro, formaFarmaceutica,
				principioAtivo, concentracao, diluente);
		
			default -> throw new IllegalArgumentException("Tipo de produto inválido.");
		};
	}		
}