package br.edu.infnet.matheus_mendes_api.modelo.dominio;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

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
	private String cnpj;
	
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