package br.edu.infnet.matheus_mendes_api.modelo.dominio.validacoes;

import br.edu.infnet.matheus_mendes_api.controladores.dto.ProdutoQuimicoDto;
import br.edu.infnet.matheus_mendes_api.excecoes.ExcecaoRecursoInvalido;

public class validarProdutoQuimico {
    public static void validarDto(ProdutoQuimicoDto dto) {
        if (dto.nomeComercial() == null || dto.nomeComercial().isBlank()) {
            throw new ExcecaoRecursoInvalido("O nome comercial do produto é obrigatório.");
        }

        if (dto.tipoProduto() == null) {
            throw new ExcecaoRecursoInvalido("O tipo de produto químico deve ser informado.");
        }

        if (dto.fabricanteId() == null) {
            throw new ExcecaoRecursoInvalido("O identificador do fabricante é obrigatório.");
        }
    }
}
