package br.edu.infnet.matheus_mendes_api.modelo.dominio.produtos;

import java.time.LocalDate;

import br.edu.infnet.matheus_mendes_api.controladores.dto.ProdutoQuimicoDto;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.Fabricante;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.enums.*;

public class ProdutoQuimicoFactory {

 public static ProdutoQuimicoBase criarProduto(ProdutoQuimicoDto dto) {
     return switch (dto.tipoProduto()) {
         case DESINFETANTE -> criarDesinfetante(
                 dto.fabricante(),
                 dto.nomeComercial(),
                 dto.registroAnvisa(),
                 dto.validadeRegistro(),
                 dto.formaFarmaceutica(),
                 dto.principioAtivo(),
                 dto.concentracao(),
                 dto.diluente(),
                 dto.usoHospitalar()
         );
         case INSETICIDA -> criarInseticida(
                 dto.fabricante(),
                 dto.nomeComercial(),
                 dto.registroAnvisa(),
                 dto.validadeRegistro(),
                 dto.formaFarmaceutica(),
                 dto.principioAtivo(),
                 dto.concentracao(),
                 dto.diluente(),
                 dto.insetoAlvo()
         );
         case RATICIDA -> criarRaticida(
                 dto.fabricante(),
                 dto.nomeComercial(),
                 dto.registroAnvisa(),
                 dto.validadeRegistro(),
                 dto.formaFarmaceutica(),
                 dto.principioAtivo(),
                 dto.concentracao(),
                 dto.diluente(),
                 dto.resistenteAHumidade()
         );
     };
 }

 public static Desinfetante criarDesinfetante(Fabricante fabricante, String nomeComercial,
                                               String registroAnvisa, LocalDate validadeRegistro,
                                               FormaFarmaceutica formaFarmaceutica, PrincipioAtivo principioAtivo,
                                               double concentracao, Diluente diluente, Boolean usoHospitalar) {
	 
     return new Desinfetante(fabricante, TipoProduto.DESINFETANTE, nomeComercial, registroAnvisa,
             validadeRegistro, formaFarmaceutica, principioAtivo, concentracao,
             diluente, usoHospitalar != null ? usoHospitalar : false);
 }

 public static Inseticida criarInseticida(Fabricante fabricante, String nomeComercial,
                                           String registroAnvisa, LocalDate validadeRegistro,
                                           FormaFarmaceutica formaFarmaceutica, PrincipioAtivo principioAtivo,
                                           double concentracao, Diluente diluente, InsetoAlvo insetoAlvo) {
	 
     return new Inseticida(fabricante, TipoProduto.INSETICIDA, nomeComercial, registroAnvisa,
             validadeRegistro, formaFarmaceutica, principioAtivo, concentracao,
             diluente, insetoAlvo != null ? insetoAlvo : InsetoAlvo.NAO_DEFINIDO);
 }

 public static Raticida criarRaticida(Fabricante fabricante, String nomeComercial,
                                      String registroAnvisa, LocalDate validadeRegistro,
                                      FormaFarmaceutica formaFarmaceutica, PrincipioAtivo principioAtivo,
                                      double concentracao, Diluente diluente, Boolean resistenteAHumidade) {
	 
     return new Raticida(fabricante, TipoProduto.RATICIDA, nomeComercial, registroAnvisa,
             validadeRegistro, formaFarmaceutica, principioAtivo, concentracao,
             diluente, resistenteAHumidade != null ? resistenteAHumidade : false);
 }
}
