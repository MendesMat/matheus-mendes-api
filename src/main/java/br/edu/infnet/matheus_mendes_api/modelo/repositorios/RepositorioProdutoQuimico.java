package br.edu.infnet.matheus_mendes_api.modelo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.produtos.ProdutoQuimicoBase;

@Repository
public interface RepositorioProdutoQuimico extends JpaRepository<ProdutoQuimicoBase, Integer> {
	boolean existsByRegistroAnvisa(String registroAnvisa);
    List<ProdutoQuimicoBase> findByNomeComercialContainingIgnoreCase(String nomeComercial);
    List<ProdutoQuimicoBase> findByConcentracaoBetween(double minimo, double maximo);
}