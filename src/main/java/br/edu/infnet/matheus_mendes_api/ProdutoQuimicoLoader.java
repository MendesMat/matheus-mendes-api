package br.edu.infnet.matheus_mendes_api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.infnet.matheus_mendes_api.model.domain.*;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.*;
import br.edu.infnet.matheus_mendes_api.model.domain.produtos.Desinfetante;
import br.edu.infnet.matheus_mendes_api.model.domain.produtos.Inseticida;
import br.edu.infnet.matheus_mendes_api.model.domain.produtos.Raticida;
import br.edu.infnet.matheus_mendes_api.model.service.ProdutoQuimicoService;

@Component
@Order(2)
public class ProdutoQuimicoLoader implements ApplicationRunner {
	
	private final ProdutoQuimicoService produtoQuimicoService;
	private final FabricanteLoader fabricanteLoader;
	
	public ProdutoQuimicoLoader(ProdutoQuimicoService produtoQuimicoService, FabricanteLoader fabricanteLoader) {
        this.produtoQuimicoService = produtoQuimicoService;
		this.fabricanteLoader = fabricanteLoader;
    }
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<Fabricante> fabricantes = fabricanteLoader.getFabricantes();
        carregarProdutosQuimicos("produtos-quimicos-listagem.csv", fabricantes);
        
        System.out.println("=== PRODUTOS QUIMICOS ===");
        produtoQuimicoService.obterLista().forEach(System.out::println);
	}
	
	private void carregarProdutosQuimicos(String caminhoArquivo, List<Fabricante> fabricantes) throws IOException {
	    
	    BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo));
	    
	    String linha = null;
	    int i = 0;
	    while ((linha = leitor.readLine()) != null) {
	        String[] campos = linha.split(",");
	        if (campos.length < 7) continue;
	        
	        Fabricante fabricante = i < fabricantes.size()
	        		? fabricantes.get(i)
	        		: fabricantes.get(fabricantes.size() - 1);

	        String nomeComercial = campos[0].trim();
	        String registroAnvisa = campos[1].trim();
	        LocalDate validade = LocalDate.parse(campos[2].trim());
	        PrincipioAtivo principioAtivo = PrincipioAtivo.valueOf(campos[3].trim());
	        double concentracao = Double.parseDouble(campos[4].trim());
	        Diluente diluente = Diluente.valueOf(campos[5].trim());
	        FormaFarmaceutica forma = FormaFarmaceutica.valueOf(campos[6].trim());

	        ProdutoQuimicoBase produto;
	        
	        if (i < 2) {
	            produto = new Inseticida(fabricante, nomeComercial, registroAnvisa,
	            		validade, forma, principioAtivo, concentracao, diluente);
	        } 
	        else if (i < 4) {
	            produto = new Raticida(fabricante, nomeComercial, registroAnvisa,
	            		validade, forma, principioAtivo, concentracao, diluente);
	        } 
	        else {
	            produto = new Desinfetante(fabricante, nomeComercial, registroAnvisa,
	            		validade, forma, principioAtivo, concentracao, diluente);
	        }

	        produtoQuimicoService.incluir(produto);
	        i++;
	    }
	    
	    leitor.close();
	}
}
