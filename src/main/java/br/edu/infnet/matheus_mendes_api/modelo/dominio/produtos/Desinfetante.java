package br.edu.infnet.matheus_mendes_api.modelo.dominio.produtos;

import java.time.LocalDate;

import br.edu.infnet.matheus_mendes_api.modelo.dominio.Fabricante;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.enums.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "desinfetantes")
public class Desinfetante extends ProdutoQuimicoBase {

    private boolean usoHospitalar;

    public Desinfetante() {}

    public Desinfetante(Fabricante fabricante, TipoProduto tipoProduto, String nomeComercial, String registroAnvisa, 
    		LocalDate validadeRegistroAnvisa, FormaFarmaceutica formaFarmaceutica, PrincipioAtivo principioAtivo, 
    		double concentracao, Diluente diluente, boolean usoHospitalar) {
        
    	super(fabricante, tipoProduto, nomeComercial, registroAnvisa, validadeRegistroAnvisa,
              formaFarmaceutica, principioAtivo, concentracao, diluente);
        
    	this.usoHospitalar = usoHospitalar;
    }

    public boolean isUsoHospitalar() { return usoHospitalar; }
    public void setUsoHospitalar(boolean usoHospitalar) { this.usoHospitalar = usoHospitalar; }
}