package br.edu.infnet.matheus_mendes_api.model.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.matheus_mendes_api.model.domain.enums.Diluente;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.FormaFarmaceutica;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.PrincipioAtivo;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.TipoAtivo;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.Embalagem;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.UnidadeMedida;

public class ProdutoQuimico {
	// === Properties ===
	private Integer id;
	
	private Fabricante fabricante;
	private String nomeComercial;
	private String registroAnvisa;
	private LocalDate validadeRegistroAnvisa;	
	
	private TipoAtivo tipoAtivo;
	private FormaFarmaceutica formaFarmaceutica;
	private PrincipioAtivo principioAtivo;
	private double concentracao;
	private Diluente diluente;
	
	private UnidadeMedida unidadeMedida;
	private Embalagem embalagem;
	private double quantidadePorEmbalagem;
	
	private List<Lote> listaLotes;
	private double quantidadeTotalEstoque;

	
	// === Constructor ===
	public ProdutoQuimico(Fabricante fabricante, String nomeComercial, String registroAnvisa,
			LocalDate validadeRegistroAnvisa, TipoAtivo tipoAtivo, FormaFarmaceutica formaFarmaceutica, PrincipioAtivo principioAtivo,
			double concentracao, Diluente diluente, UnidadeMedida unidadeMedida,
			Embalagem embalagem, double quantidadePorEmbalagem) {
		this.fabricante = fabricante;
		this.nomeComercial = nomeComercial;
		this.registroAnvisa = registroAnvisa;
		this.validadeRegistroAnvisa = validadeRegistroAnvisa;
		this.tipoAtivo = tipoAtivo;
		this.formaFarmaceutica = formaFarmaceutica;
		this.principioAtivo = principioAtivo;
		this.concentracao = concentracao;
		this.diluente = diluente;
		this.unidadeMedida = unidadeMedida;
		this.embalagem = embalagem;
		this.quantidadePorEmbalagem = quantidadePorEmbalagem;
		this.listaLotes = new ArrayList<>();
		this.quantidadeTotalEstoque = 0;
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
	
	public TipoAtivo getTipoAtivo() {
		return tipoAtivo;
	}
	
	public void setTipoAtivo(TipoAtivo tipoAtivo) {
		this.tipoAtivo = tipoAtivo;
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
	
	
	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}
	
	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}
	
	public Embalagem getEmbalagem() {
		return embalagem;
	}
	
	public void setEmbalagem(Embalagem embalagem) {
		this.embalagem = embalagem;
	}
	
	public double getQuantidadePorEmbalagem() {
		return quantidadePorEmbalagem;
	}
	
	public void setQuantidadePorEmbalagem(double quantidadePorEmbalagem) {
		this.quantidadePorEmbalagem = quantidadePorEmbalagem;
	}
	
	public double getQuantidadeTotalEstoque() {
		return quantidadeTotalEstoque;
	}
	
	public void setQuantidadeTotalEstoque(double quantidadeTotalEstoque) {
		this.quantidadeTotalEstoque = quantidadeTotalEstoque;
	}
	
	public List<Lote> getLote() {
		return listaLotes;
	}
	
	// === Methods ===
	public void adicionarLote(Lote lote) {
		if(lote == null) { throw new IllegalArgumentException("Não é possível adicionar um lote nulo."); }
		
		listaLotes.add(lote);
	}
	
	public void removerLote(Lote lote) {
		if(lote == null) { throw new IllegalArgumentException("Não é possível remover um lote nulo."); }
		
		boolean contem = listaLotes.remove(lote);
		if(!contem) throw new IllegalArgumentException("Lote não encontrado na lista e não pôde ser removido.");
	}
	
	@Override
	public String toString() {
	    return String.format(
    		"Id .....................: %d\n" +
	        "Fabricante .............: %-20s\n" +
	        "Nome Comercial .........: %-20s\n" +
	        "Registro ANVISA ........: %-15s\n" +
	        "Validade do Registro ...: %-10s\n" +
	        "Tipo Ativo .............: %-15s\n" +
	        "Princípio Ativo ........: %-15s\n" +
	        "Concentração ...........: %-6.2f\n" +
	        "Diluente ...............: %-10s\n" +
	        "Forma Farmacêutica .....: %-10s\n" +
	        "Unidade de Medida ......: %-5s\n" +
	        "Embalagem ..............: %-10s\n" +
	        "Qtd. por Embalagem .....: %-6.2f\n" +
	        "Estoque Atual ..........: %-6.2f\n",
	        
	        id,
	        fabricante.getNome(),
	        nomeComercial,
	        registroAnvisa,
	        validadeRegistroAnvisa,
	        tipoAtivo,
	        principioAtivo,
	        concentracao,
	        diluente,
	        formaFarmaceutica,
	        unidadeMedida,
	        embalagem,
	        quantidadePorEmbalagem,
	        quantidadeTotalEstoque
	    );
	}
	
	public void detalharFabricante() {
		fabricante.toString();
	}
	
	public void detalharLotes() {
		if(listaLotes.isEmpty()) {
			System.out.println("Nenhum lote cadastrado para este produto.");
			return;
		}
		
		for (int i = 0; i < listaLotes.size(); i++) {
		    System.out.println("Lote #" + (i + 1));
		    System.out.println(listaLotes.get(i).toString());
		}

	}
}