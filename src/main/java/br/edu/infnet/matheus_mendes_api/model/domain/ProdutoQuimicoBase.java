package br.edu.infnet.matheus_mendes_api.model.domain;

import java.time.LocalDate;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.Diluente;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.FormaFarmaceutica;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.PrincipioAtivo;

public abstract class ProdutoQuimicoBase {
	// === Properties ===
	private Integer id;
	
	private Fabricante fabricante;
	private String nomeComercial;
	private String registroAnvisa;
	private LocalDate validadeRegistroAnvisa;	
	
	private FormaFarmaceutica formaFarmaceutica;
	private PrincipioAtivo principioAtivo;
	private double concentracao;
	private Diluente diluente;

	
	// === Constructor ===
	public ProdutoQuimicoBase(Fabricante fabricante, String nomeComercial, String registroAnvisa,
			LocalDate validadeRegistroAnvisa, FormaFarmaceutica formaFarmaceutica, PrincipioAtivo principioAtivo,
			double concentracao, Diluente diluente) {
		this.fabricante = fabricante;
		this.nomeComercial = nomeComercial;
		this.registroAnvisa = registroAnvisa;
		this.validadeRegistroAnvisa = validadeRegistroAnvisa;
		this.formaFarmaceutica = formaFarmaceutica;
		this.principioAtivo = principioAtivo;
		this.concentracao = concentracao;
		this.diluente = diluente;
	}

	// === Getters and Setters ===
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Fabricante getFabricante() {
		return fabricante;
	}
	
	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	
	public String getNomeComercial() {
		return nomeComercial;
	}
	
	public void setNomeComercial(String nomeComercial) {
		this.nomeComercial = nomeComercial;
	}
	
	public String getRegistroAnvisa() {
		return registroAnvisa;
	}
	
	public void setRegistroAnvisa(String registroAnvisa) {
		this.registroAnvisa = registroAnvisa;
	}
	
	public LocalDate getValidadeRegistroAnvisa() {
		return validadeRegistroAnvisa;
	}
	
	public void setValidadeRegistroAnvisa(LocalDate validadeRegistroAnvisa) {
		this.validadeRegistroAnvisa = validadeRegistroAnvisa;
	}
	
	public FormaFarmaceutica getFormaFarmaceutica() {
		return formaFarmaceutica;
	}
	
	public void setFormaFarmaceutica(FormaFarmaceutica formaFarmaceutica) {
		this.formaFarmaceutica = formaFarmaceutica;
	}
	
	public PrincipioAtivo getPrincipioAtivo() {
		return principioAtivo;
	}
	
	public void setPrincipioAtivo(PrincipioAtivo principioAtivo) {
		this.principioAtivo = principioAtivo;
	}
	
	public double getConcentracao() {
		return concentracao;
	}
	
	public void setConcentracao(double concentracao) {
		this.concentracao = concentracao;
	}
	
	public Diluente getDiluente() {
		return diluente;
	}
	
	public void setDiluente(Diluente diluente) {
		this.diluente = diluente;
	}
	
	@Override
	public String toString() {
	    return String.format(
    		"Id .....................: %d\n" +
	        "Fabricante .............: %-20s\n" +
	        "Nome Comercial .........: %-20s\n" +
	        "Registro ANVISA ........: %-15s\n" +
	        "Validade do Registro ...: %-10s\n" +
	        "Princípio Ativo ........: %-15s\n" +
	        "Concentração ...........: %-6.2f\n" +
	        "Diluente ...............: %-10s\n" +
	        "Forma Farmacêutica .....: %-10s\n",
	        
	        id,
	        fabricante.getNome(),
	        nomeComercial,
	        registroAnvisa,
	        validadeRegistroAnvisa,
	        principioAtivo,
	        concentracao,
	        diluente,
	        formaFarmaceutica
	    );
	}
	
	public void detalharFabricante() {
		fabricante.toString();
	}
}