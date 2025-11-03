package br.edu.infnet.matheus_mendes_api.controladores.dto;

import java.time.LocalDate;

import br.edu.infnet.matheus_mendes_api.modelo.dominio.enums.*;

public record ProdutoQuimicoDto(
        Integer id,
        FabricanteDto fabricante,
        TipoProduto tipoProduto,
        String nomeComercial,
        String registroAnvisa,
        LocalDate validadeRegistro,
        Boolean ativo,
        FormaFarmaceutica formaFarmaceutica,
        PrincipioAtivo principioAtivo,
        Double concentracao,
        Diluente diluente,
        Boolean usoHospitalar,
        InsetoAlvo insetoAlvo,
        Boolean resistenteAHumidade
) {}