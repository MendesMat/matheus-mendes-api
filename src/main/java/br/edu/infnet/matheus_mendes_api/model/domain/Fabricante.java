package br.edu.infnet.matheus_mendes_api.model.domain;

public class Fabricante {
	// === Properties ===
	private Integer id;
	private String nome;
	private String cnpj;
	
	// === Constructor ===
	public Fabricante(String nome, String cnpj) {
		this.nome = nome;
		this.cnpj = cnpj;
	}
	
	// === Getters and Setters ===
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	// === Methods ===
	public String toString() {
		return String.format(
			"Nome ...: %s\n" +
			"CNPJ ...: %s\n",
			
			nome,
			cnpj
		);
	}
}