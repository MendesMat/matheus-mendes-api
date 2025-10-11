package br.edu.infnet.matheus_mendes_api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.infnet.matheus_mendes_api.model.domain.Fabricante;
import br.edu.infnet.matheus_mendes_api.model.service.FabricanteService;

@Component
@Order(1)
public class FabricanteLoader implements ApplicationRunner {

    private final FabricanteService fabricanteService;

    public FabricanteLoader(FabricanteService fabricanteService) {
        this.fabricanteService = fabricanteService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
    	
        carregarFabricantes("fabricantes-listagem.csv");

        System.out.println("\n=== FABRICANTES ===");
        Collection<Fabricante> fabricantes = fabricanteService.obterLista();
        fabricantes.forEach(System.out::println);
    }

    private void carregarFabricantes(String caminhoArquivo) throws IOException {
    	
        BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo));
        
        String linha;
        while ((linha = leitor.readLine()) != null) {
            String[] campos = linha.split(",");
            
            String nome = campos[0].trim();
            String cnpj = campos[1].trim();

            Fabricante fabricante = new Fabricante(nome, cnpj);
            fabricanteService.incluir(fabricante);
        }
        
        leitor.close();
    }

	public List<Fabricante> getFabricantes() {
		return new ArrayList<>(fabricanteService.obterLista());
	}
}
