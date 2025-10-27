package br.edu.infnet.matheus_mendes_api.modelo.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.Fabricante;

@Repository
public interface RepositorioFabricante extends JpaRepository<Fabricante, Integer> {}