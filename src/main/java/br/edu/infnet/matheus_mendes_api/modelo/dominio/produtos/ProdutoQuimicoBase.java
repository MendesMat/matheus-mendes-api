package br.edu.infnet.matheus_mendes_api.modelo.dominio.produtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.edu.infnet.matheus_mendes_api.modelo.dominio.Fabricante;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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
	@NotNull(message = "O fabricante é obrigatório.")
	@JsonBackReference
	private Fabricante fabricante;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "O tipo de produto é obrigatório.")
	private TipoProduto tipoProduto;
	
	@NotBlank(message = "O nome comercial é obrigatório.")
	private String nomeComercial;
	
	@NotBlank(message = "O registro ANVISA é obrigatório.")
	@Pattern(
	    regexp = "ANV\\d{6}",
	    message = "O registro ANVISA deve estar no formato ANV123456"
	)
	private String registroAnvisa;
	
	@Future(message = "A validade do registro ANVISA deve estar no futuro.")
	private LocalDate validadeRegistro;
	
	private boolean ativo;
	
	@Enumerated(EnumType.STRING)
	private FormaFarmaceutica formaFarmaceutica;
	
	@Enumerated(EnumType.STRING)
	private PrincipioAtivo principioAtivo;
	
	@DecimalMin(value = "0.0001", inclusive = true, message = "A concentração mínima deve ser maior que 0.")
	@DecimalMax(value = "99.0", inclusive = true, message = "A concentração máxima permitida é 99%.")
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