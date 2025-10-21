package br.edu.infnet.matheus_mendes_api.modelo.dominio.produtos;

import java.time.LocalDate;

import br.edu.infnet.matheus_mendes_api.modelo.dominio.enums.Diluente;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.enums.FormaFarmaceutica;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.enums.PrincipioAtivo;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.enums.TipoProduto;

public class Raticida extends ProdutoQuimicoBase{
	// === Constructor ===
	public Raticida() { }
	
	public Raticida(Integer fabrincateId, TipoProduto tipoProduto, String nomeComercial, String registroAnvisa,
			LocalDate validadeRegistroAnvisa, FormaFarmaceutica formaFarmaceutica,
			PrincipioAtivo principioAtivo, double concentracao, Diluente diluente) {
		super(fabrincateId, tipoProduto, nomeComercial, registroAnvisa, validadeRegistroAnvisa, formaFarmaceutica, principioAtivo,
				concentracao, diluente);
	}

}
