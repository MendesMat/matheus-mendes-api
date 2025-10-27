package br.edu.infnet.matheus_mendes_api.modelo.dominio.produtos;

import java.time.LocalDate;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.Fabricante;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.enums.*;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "produtos")
public abstract class ProdutoQuimicoBase {
	// === Properties ===
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "fabricante_id")
	private Fabricante fabricante;
	@Enumerated(EnumType.STRING)
	private TipoProduto tipoProduto;
	private String nomeComercial;
	private String registroAnvisa;
	private LocalDate validadeRegistro;	
	private boolean ativo;
	

	@Enumerated(EnumType.STRING)
	private FormaFarmaceutica formaFarmaceutica;
	@Enumerated(EnumType.STRING)
	private PrincipioAtivo principioAtivo;
	private double concentracao;
	@Enumerated(EnumType.STRING)
	private Diluente diluente;
	
	// === Constructor ===
	public ProdutoQuimicoBase() { }
	
	public ProdutoQuimicoBase(Fabricante fabricante, TipoProduto tipoProduto, String nomeComercial, String registroAnvisa,
			LocalDate validadeRegistroAnvisa, FormaFarmaceutica formaFarmaceutica, PrincipioAtivo principioAtivo,
			double concentracao, Diluente diluente) {
		this.fabricante = fabricante;
		this.tipoProduto = tipoProduto;
		this.nomeComercial = nomeComercial;
		this.registroAnvisa = registroAnvisa;
		this.validadeRegistro = validadeRegistroAnvisa;
		this.ativo = true;
		this.formaFarmaceutica = formaFarmaceutica;
		this.principioAtivo = principioAtivo;
		this.concentracao = concentracao;
		this.diluente = diluente;
	}

	// === Getters and Setters ===
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	
	public Fabricante getFabricante() { return fabricante; }
	public void setFabricante(Fabricante fabricante) { this.fabricante = fabricante; }
	
	public TipoProduto getTipoProduto() { return tipoProduto; }
	public void setTipoProduto(TipoProduto tipoProduto) { this.tipoProduto = tipoProduto; }
	
	public String getNomeComercial() { return nomeComercial; }
	public void setNomeComercial(String nomeComercial) { this.nomeComercial = nomeComercial; }
	
	public String getRegistroAnvisa() { return registroAnvisa; }
	public void setRegistroAnvisa(String registroAnvisa) { this.registroAnvisa = registroAnvisa; }
	
	public LocalDate getValidadeRegistroAnvisa() { return validadeRegistro; }
	public void setValidadeRegistroAnvisa(LocalDate validadeRegistroAnvisa) { this.validadeRegistro = validadeRegistroAnvisa; }
	
	public boolean getAtivo() { return ativo; }
	public void setAtivo(boolean ativo) { this.ativo = ativo; }
	
	public FormaFarmaceutica getFormaFarmaceutica() { return formaFarmaceutica; }
	public void setFormaFarmaceutica(FormaFarmaceutica formaFarmaceutica) { this.formaFarmaceutica = formaFarmaceutica; }
	
	public PrincipioAtivo getPrincipioAtivo() { return principioAtivo; }
	public void setPrincipioAtivo(PrincipioAtivo principioAtivo) { this.principioAtivo = principioAtivo; }
	
	public double getConcentracao() { return concentracao; }
	public void setConcentracao(double concentracao) { this.concentracao = concentracao; }
	
	public Diluente getDiluente() { return diluente; }
	public void setDiluente(Diluente diluente) { this.diluente = diluente; }
	
	// === Methods ===
	@Override
	public String toString() {
	    return String.format(
    		"Id .....................: %d\n"     +
			"Fabricante .............: %-20s\n"  +
			"Tipo de Produto.........: %-10s\n"  +
	        "Nome Comercial .........: %-20s\n"  +
	        "Registro ANVISA ........: %-15s\n"  +
	        "Validade do Registro ...: %-10s\n"  +
	        "Ativo...................: %-10s\n"  +
	        "Princípio Ativo ........: %-15s\n"  +
	        "Concentração ...........: %-6.2f\n" +
	        "Diluente ...............: %-10s\n"  +
	        "Forma Farmacêutica .....: %-10s\n"  ,
	        
	        id,
	        fabricante.getNome(),
	        tipoProduto,
	        nomeComercial,
	        registroAnvisa,
	        validadeRegistro,
	        ativo,
	        principioAtivo,
	        concentracao,
	        diluente,
	        formaFarmaceutica
	    );
	}
}