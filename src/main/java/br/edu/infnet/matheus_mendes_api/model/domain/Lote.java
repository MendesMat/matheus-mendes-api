package br.edu.infnet.matheus_mendes_api.model.domain;

import java.time.LocalDate;

public class Lote {
	// === Properties ===
	private String codigoLote;
	private LocalDate validadeLote;
	private double quantidadeEstoqueAtual;
	
	// === Constructor ===
	public Lote(String codigoLote, LocalDate validadeLote, double quantidadeEstoqueAtual) {
		this.codigoLote = codigoLote;
		this.validadeLote = validadeLote;
		this.quantidadeEstoqueAtual = quantidadeEstoqueAtual;
	}
	
	// === Getters and Setters ===
	public String getCodigoLote() {
		return codigoLote;
	}

	public void setCodigoLote(String codigoLote) {
		this.codigoLote = codigoLote;
	}
	
	public LocalDate getValidadeLote() {
		return validadeLote;
	}
	
	public void setValidadeLote(LocalDate validadeLote) {
		this.validadeLote = validadeLote;
	}
	
	public double getQuantidadeEstoqueAtual() {
		return quantidadeEstoqueAtual;
	}
	
	public void setQuantidadeEstoqueAtual(double quantidadeEstoqueAtual) {
		this.quantidadeEstoqueAtual = quantidadeEstoqueAtual;
	}
	
	// === Methods ===
	public String toString() {
		return String.format(
			"Codigo do Lote .....: %s\n" +
			"Validade do Lote ...: %s\n" +
			"Estoque Atual ......: %s\n",
			
			codigoLote,
			validadeLote,
			quantidadeEstoqueAtual
		);
	}
}
