package br.edu.infnet.matheus_mendes_api.controladores.dto;

import java.time.LocalDate;

import br.edu.infnet.matheus_mendes_api.modelo.dominio.enums.Diluente;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.enums.FormaFarmaceutica;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.enums.PrincipioAtivo;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.enums.TipoProduto;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.produtos.ProdutoQuimicoBase;

public record ProdutoQuimicoDto(
	    Integer id,
	    Integer fabricanteId,
	    TipoProduto tipoProduto,
	    String nomeComercial,
	    String registroAnvisa,
	    boolean ativo,
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
	            entity.getAtivo(),
	            entity.getValidadeRegistroAnvisa(),
	            entity.getFormaFarmaceutica(),
	            entity.getPrincipioAtivo(),
	            entity.getConcentracao(),
	            entity.getDiluente()
	        );
	    }
	}
