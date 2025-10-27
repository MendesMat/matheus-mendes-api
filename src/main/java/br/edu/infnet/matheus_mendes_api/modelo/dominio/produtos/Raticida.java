package br.edu.infnet.matheus_mendes_api.modelo.dominio.produtos;

import java.time.LocalDate;

import br.edu.infnet.matheus_mendes_api.modelo.dominio.Fabricante;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.enums.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "raticidas")
public class Raticida extends ProdutoQuimicoBase{
	// === Constructor ===
	public Raticida() { }
	
	public Raticida(Fabricante fabricante, TipoProduto tipoProduto, String nomeComercial, String registroAnvisa,
			LocalDate validadeRegistroAnvisa, FormaFarmaceutica formaFarmaceutica,
			PrincipioAtivo principioAtivo, double concentracao, Diluente diluente) {
		super(fabricante, tipoProduto, nomeComercial, registroAnvisa, validadeRegistroAnvisa, formaFarmaceutica, principioAtivo,
				concentracao, diluente);
	}

}