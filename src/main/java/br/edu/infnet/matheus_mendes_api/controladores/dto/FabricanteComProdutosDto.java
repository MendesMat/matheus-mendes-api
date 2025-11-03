package br.edu.infnet.matheus_mendes_api.controladores.dto;

import java.util.List;

public record FabricanteComProdutosDto(
	    Integer id,
	    String nome,
	    String cnpj,
	    List<ProdutoQuimicoDto> produtos
) {}