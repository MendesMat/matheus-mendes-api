package br.edu.infnet.matheus_mendes_api.modelo.dominio;

import java.util.List;

import br.edu.infnet.matheus_mendes_api.modelo.dominio.produtos.ProdutoQuimicoBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="fabricantes")
public class Fabricante {
	// === Properties ===
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "O nome do fabricante é obrigatório.")
	private String nome;
	
	@NotBlank(message = "O CNPJ do fabricante é obrigatório.")
	@Pattern(
	    regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}",
	    message = "O CNPJ deve estar no formato XX.XXX.XXX/XXXX-XX"
	)
	private String cnpj;
	
	@OneToMany(mappedBy = "fabricante", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<ProdutoQuimicoBase> produtos;

	// === Constructor ===
	public Fabricante() {}
	
	public Fabricante(String nome, String cnpj) {
		this.nome = nome;
		this.cnpj = cnpj;
	}
	
	// === Getters and Setters ===
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	
	public String getCnpj() { return cnpj; }
	public void setCnpj(String cnpj) { this.cnpj = cnpj; }
	
	public List<ProdutoQuimicoBase> getProdutos() { return produtos; }
	
	// === Methods ===
	public String toString() {
		return String.format(
			"Nome ...: %s\n" +
			"CNPJ ...: %s\n",
			
			nome,
			cnpj
		);
	}
	
	public void adicionarProduto(ProdutoQuimicoBase produto) {
	    if (produtos != null && !produtos.contains(produto)) {
	        produtos.add(produto);
	    }
	}
}