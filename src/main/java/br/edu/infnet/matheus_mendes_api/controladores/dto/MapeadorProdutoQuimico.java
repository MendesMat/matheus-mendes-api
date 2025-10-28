package br.edu.infnet.matheus_mendes_api.controladores.dto;

import br.edu.infnet.matheus_mendes_api.modelo.dominio.enums.InsetoAlvo;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.produtos.*;
public class MapeadorProdutoQuimico {

    public static ProdutoQuimicoDto aPartirDeEntidade(ProdutoQuimicoBase produto) {
        CamposEspecificos tipoDeProduto = extrairTipoDeProduto(produto);

        return new ProdutoQuimicoDto(
            produto.getId(),
            produto.getFabricante(),
            produto.getTipoProduto(),
            produto.getNomeComercial(),
            produto.getRegistroAnvisa(),
            produto.getValidadeRegistroAnvisa(),
            Boolean.valueOf(produto.getAtivo()),
            produto.getFormaFarmaceutica(),
            produto.getPrincipioAtivo(),
            Double.valueOf(produto.getConcentracao()),
            produto.getDiluente(),
            tipoDeProduto.usoHospitalar(),
            tipoDeProduto.insetoAlvo(),
            tipoDeProduto.resistenteAHumidade()
        );
    }

    private static CamposEspecificos extrairTipoDeProduto(ProdutoQuimicoBase produto) {
        Boolean usoHospitalar = null;
        InsetoAlvo insetoAlvo = null;
        Boolean resistenteAHumidade = null;

        switch (produto) {
            case Desinfetante d -> usoHospitalar = d.isUsoHospitalar();
            case Inseticida i -> insetoAlvo = i.getInsetoAlvo();
            case Raticida r -> resistenteAHumidade = r.isResistenteAHumidade();
            
            default -> throw new IllegalArgumentException(
                "Tipo de produto desconhecido: " + produto.getClass().getName()
            );
        }

        return new CamposEspecificos(usoHospitalar, insetoAlvo, resistenteAHumidade);
    }

    private record CamposEspecificos(Boolean usoHospitalar, InsetoAlvo insetoAlvo, Boolean resistenteAHumidade) {}
}