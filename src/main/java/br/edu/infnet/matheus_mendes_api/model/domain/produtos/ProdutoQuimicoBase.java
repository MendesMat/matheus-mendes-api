package br.edu.infnet.matheus_mendes_api.model.domain.produtos;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import br.edu.infnet.matheus_mendes_api.model.domain.enums.Diluente;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.FormaFarmaceutica;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.PrincipioAtivo;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.TipoProduto;

public abstract class ProdutoQuimicoBase {
	// === Properties ===
	private Integer id;
	
	private Integer fabricanteId;
	private TipoProduto tipoProduto;
	private String nomeComercial;
	private String registroAnvisa;
	private LocalDate validadeRegistro;	
	
	private FormaFarmaceutica formaFarmaceutica;
	private PrincipioAtivo principioAtivo;
	private double concentracao;
	private Diluente diluente;

	private static Map<Integer, String> fabricantesMap = new HashMap<>();
	
	// === Constructor ===
	public ProdutoQuimicoBase() { }
	
	public ProdutoQuimicoBase(Integer fabricanteId, TipoProduto tipoProduto, String nomeComercial, String registroAnvisa,
			LocalDate validadeRegistroAnvisa, FormaFarmaceutica formaFarmaceutica, PrincipioAtivo principioAtivo,
			double concentracao, Diluente diluente) {
		this.fabricanteId = fabricanteId;
		this.tipoProduto = tipoProduto;
		this.nomeComercial = nomeComercial;
		this.registroAnvisa = registroAnvisa;
		this.validadeRegistro = validadeRegistroAnvisa;
		this.formaFarmaceutica = formaFarmaceutica;
		this.principioAtivo = principioAtivo;
		this.concentracao = concentracao;
		this.diluente = diluente;
	}

	// === Getters and Setters ===
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	
	public Integer getFabricanteId() { return fabricanteId; }
	public void setFabricanteId(Integer fabricanteId) { this.fabricanteId = fabricanteId; }
	
	public TipoProduto getTipoProduto() { return tipoProduto; }
	public void setTipoProduto(TipoProduto tipoProduto) { this.tipoProduto = tipoProduto; }
	
	public String getNomeComercial() { return nomeComercial; }
	public void setNomeComercial(String nomeComercial) { this.nomeComercial = nomeComercial; }
	
	public String getRegistroAnvisa() { return registroAnvisa; }
	public void setRegistroAnvisa(String registroAnvisa) { this.registroAnvisa = registroAnvisa; }
	
	public LocalDate getValidadeRegistroAnvisa() { return validadeRegistro; }
	public void setValidadeRegistroAnvisa(LocalDate validadeRegistroAnvisa) { this.validadeRegistro = validadeRegistroAnvisa; }
	
	public FormaFarmaceutica getFormaFarmaceutica() { return formaFarmaceutica; }
	public void setFormaFarmaceutica(FormaFarmaceutica formaFarmaceutica) { this.formaFarmaceutica = formaFarmaceutica; }
	
	public PrincipioAtivo getPrincipioAtivo() { return principioAtivo; }
	public void setPrincipioAtivo(PrincipioAtivo principioAtivo) { this.principioAtivo = principioAtivo; }
	
	public double getConcentracao() { return concentracao; }
	public void setConcentracao(double concentracao) { this.concentracao = concentracao; }
	
	public Diluente getDiluente() { return diluente; }
	public void setDiluente(Diluente diluente) { this.diluente = diluente; }
	
	public static void setFabricantesMap(Map<Integer, String> map) { fabricantesMap = map; }
	
	@Override
	public String toString() {
		var nomeFabricante = fabricantesMap.get(fabricanteId);
		
	    return String.format(
    		"Id .....................: %d\n"     +
			"Fabricante .............: %-20s\n"  +
			"Tipo de Produto.........: %-10s\n"  +
	        "Nome Comercial .........: %-20s\n"  +
	        "Registro ANVISA ........: %-15s\n"  +
	        "Validade do Registro ...: %-10s\n"  +
	        "Princípio Ativo ........: %-15s\n"  +
	        "Concentração ...........: %-6.2f\n" +
	        "Diluente ...............: %-10s\n"  +
	        "Forma Farmacêutica .....: %-10s\n"  ,
	        
	        id,
	        nomeFabricante,
	        tipoProduto,
	        nomeComercial,
	        registroAnvisa,
	        validadeRegistro,
	        principioAtivo,
	        concentracao,
	        diluente,
	        formaFarmaceutica
	    );
	}
}