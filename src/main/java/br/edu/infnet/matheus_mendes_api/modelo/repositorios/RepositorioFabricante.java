package br.edu.infnet.matheus_mendes_api.modelo.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.Fabricante;

@Repository
public interface RepositorioFabricante extends JpaRepository<Fabricante, Integer> {
	boolean existsByCnpj(String cnpj);
	
	@Query("SELECT f FROM Fabricante f LEFT JOIN FETCH f.produtos WHERE f.id = :id")
    Optional<Fabricante> findByIdComProdutos(@Param("id") Integer id);
}