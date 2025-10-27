package br.edu.infnet.matheus_mendes_api.controladores.dto;

import java.time.LocalDate;

import br.edu.infnet.matheus_mendes_api.modelo.dominio.Fabricante;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.enums.*;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.produtos.ProdutoQuimicoBase;

public record ProdutoQuimicoDto(
	    Integer id,
	    Fabricante fabricante,
	    TipoProduto tipoProduto,
	    String nomeComercial,
	    String registroAnvisa,
	    LocalDate validadeRegistro,
	    boolean ativo,
	    FormaFarmaceutica formaFarmaceutica,
	    PrincipioAtivo principioAtivo,
	    double concentracao,
	    Diluente diluente
	) {
	    public static ProdutoQuimicoDto fromEntity(ProdutoQuimicoBase entity) {
	        return new ProdutoQuimicoDto(
	            entity.getId(),
	            entity.getFabricante(),
	            entity.getTipoProduto(),
	            entity.getNomeComercial(),
	            entity.getRegistroAnvisa(),
	            entity.getValidadeRegistroAnvisa(),
	            entity.getAtivo(),
	            entity.getFormaFarmaceutica(),
	            entity.getPrincipioAtivo(),
	            entity.getConcentracao(),
	            entity.getDiluente()
	        );
	    }
	}