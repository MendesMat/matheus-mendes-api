package br.edu.infnet.matheus_mendes_api.modelo.dominio.produtos;

import java.time.LocalDate;

import br.edu.infnet.matheus_mendes_api.modelo.dominio.Fabricante;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.enums.*;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "inseticidas")
public class Inseticida extends ProdutoQuimicoBase {

    @Enumerated(EnumType.STRING)
    private InsetoAlvo insetoAlvo;

    public Inseticida() {}

    public Inseticida(Fabricante fabricante, TipoProduto tipoProduto, String nomeComercial, String registroAnvisa, 
    		LocalDate validadeRegistroAnvisa, FormaFarmaceutica formaFarmaceutica, PrincipioAtivo principioAtivo, 
    		double concentracao, Diluente diluente, InsetoAlvo insetoAlvo) {
        
    	super(fabricante, tipoProduto, nomeComercial, registroAnvisa, validadeRegistroAnvisa,
              formaFarmaceutica, principioAtivo, concentracao, diluente);
        
    	this.insetoAlvo = insetoAlvo;
    }

    public InsetoAlvo getInsetoAlvo() { return insetoAlvo; }
    public void setInsetoAlvo(InsetoAlvo insetoAlvo) { this.insetoAlvo = insetoAlvo; }
}