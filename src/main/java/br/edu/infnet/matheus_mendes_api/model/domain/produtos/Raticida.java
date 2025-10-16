package br.edu.infnet.matheus_mendes_api.model.domain.produtos;

import java.time.LocalDate;

import br.edu.infnet.matheus_mendes_api.model.domain.Fabricante;
import br.edu.infnet.matheus_mendes_api.model.domain.ProdutoQuimicoBase;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.Diluente;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.FormaFarmaceutica;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.PrincipioAtivo;

public class Raticida extends ProdutoQuimicoBase{

	// === Constructor ===
	public Raticida(Fabricante fabricante, String nomeComercial, String registroAnvisa,
			LocalDate validadeRegistroAnvisa, FormaFarmaceutica formaFarmaceutica,
			PrincipioAtivo principioAtivo, double concentracao, Diluente diluente) {
		super(fabricante, nomeComercial, registroAnvisa, validadeRegistroAnvisa, formaFarmaceutica, principioAtivo,
				concentracao, diluente);
	}

}
