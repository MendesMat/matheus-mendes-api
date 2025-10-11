package br.edu.infnet.matheus_mendes_api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.infnet.matheus_mendes_api.model.domain.Fabricante;
import br.edu.infnet.matheus_mendes_api.model.domain.ProdutoQuimico;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.Diluente;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.Embalagem;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.FormaFarmaceutica;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.PrincipioAtivo;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.TipoAtivo;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.UnidadeMedida;
import br.edu.infnet.matheus_mendes_api.model.service.ProdutoQuimicoService;

@Component
@Order(2)
public class ProdutoQuimicoLoader implements ApplicationRunner {
	
	private final ProdutoQuimicoService produtoQuimicoService;
	private FabricanteLoader fabricanteLoader;
	
	public ProdutoQuimicoLoader(FabricanteLoader fabricanteLoader) {
        this.produtoQuimicoService = new ProdutoQuimicoService();
		this.fabricanteLoader = fabricanteLoader;
    }
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		List<Fabricante> fabricantes = fabricanteLoader.getFabricantes();
        carregarProdutosQuimicos("produtos-quimicos-listagem.csv", fabricantes);
        
        System.out.println("=== Produtos Cadastrados ===");
        Collection<ProdutoQuimico> produtos = produtoQuimicoService.obterLista();
        produtos.forEach(produto -> System.out.println(produto.toString()));
	}
	
	private void carregarProdutosQuimicos(String caminhoArquivo, List<Fabricante> fabricantes) throws IOException {
		
	    BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo));
	    
	    String linha = null;
	    int i = 0;
	    while ((linha = leitor.readLine()) != null) {
	        String[] campos = linha.split(",");
	        
	        Fabricante fabricante = i < fabricantes.size() 
	        		? fabricantes.get(i) : fabricantes.get(fabricantes.size()-1);
	
	        ProdutoQuimico produto = new ProdutoQuimico(
                    fabricante,
                    campos[0].trim(),
                    campos[1].trim(),
                    LocalDate.parse(campos[2].trim()),
                    TipoAtivo.valueOf(campos[3].trim()),
                    FormaFarmaceutica.valueOf(campos[4].trim()),
                    PrincipioAtivo.valueOf(campos[5].trim()),
                    Double.parseDouble(campos[6].trim()),
                    Diluente.valueOf(campos[7].trim()),
                    UnidadeMedida.valueOf(campos[8].trim()),
                    Embalagem.valueOf(campos[9].trim()),
                    Double.parseDouble(campos[10].trim())
            );
	        
	        produtoQuimicoService.incluir(produto);
	        i++;	        
	    }
	    
	    leitor.close();
	}
}