package br.edu.infnet.matheus_mendes_api.model.domain.produtos;

import java.time.LocalDate;

import br.edu.infnet.matheus_mendes_api.model.domain.enums.Diluente;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.FormaFarmaceutica;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.PrincipioAtivo;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.TipoProduto;

public class Inseticida extends ProdutoQuimicoBase{
	// === Constructor ===
	public Inseticida() { }
	
	public Inseticida(Integer fabrincateId, TipoProduto tipoProduto, String nomeComercial, String registroAnvisa,
			LocalDate validadeRegistroAnvisa, FormaFarmaceutica formaFarmaceutica,
			PrincipioAtivo principioAtivo, double concentracao, Diluente diluente) {
		super(fabrincateId, tipoProduto, nomeComercial, registroAnvisa, validadeRegistroAnvisa, formaFarmaceutica, principioAtivo,
				concentracao, diluente);
	}
}
