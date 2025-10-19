package br.edu.infnet.matheus_mendes_api.controllers.dto;

import java.time.LocalDate;

import br.edu.infnet.matheus_mendes_api.model.domain.enums.Diluente;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.FormaFarmaceutica;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.PrincipioAtivo;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.TipoProduto;
import br.edu.infnet.matheus_mendes_api.model.domain.produtos.ProdutoQuimicoBase;

public record ProdutoQuimicoDto(
	    Integer id,
	    Integer fabricanteId,
	    TipoProduto tipoProduto,
	    String nomeComercial,
	    String registroAnvisa,
	    LocalDate validadeRegistro,
	    FormaFarmaceutica formaFarmaceutica,
	    PrincipioAtivo principioAtivo,
	    double concentracao,
	    Diluente diluente
	) {
	    public static ProdutoQuimicoDto fromEntity(ProdutoQuimicoBase entity) {
	        return new ProdutoQuimicoDto(
	            entity.getId(),
	            entity.getFabricanteId(),
	            entity.getTipoProduto(),
	            entity.getNomeComercial(),
	            entity.getRegistroAnvisa(),
	            entity.getValidadeRegistroAnvisa(),
	            entity.getFormaFarmaceutica(),
	            entity.getPrincipioAtivo(),
	            entity.getConcentracao(),
	            entity.getDiluente()
	        );
	    }
	}
