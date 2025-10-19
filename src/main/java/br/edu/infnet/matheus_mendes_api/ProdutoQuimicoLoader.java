package br.edu.infnet.matheus_mendes_api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.infnet.matheus_mendes_api.model.domain.Fabricante;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.Diluente;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.FormaFarmaceutica;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.PrincipioAtivo;
import br.edu.infnet.matheus_mendes_api.model.domain.enums.TipoProduto;
import br.edu.infnet.matheus_mendes_api.model.domain.produtos.ProdutoQuimicoBase;
import br.edu.infnet.matheus_mendes_api.model.domain.produtos.ProdutoQuimicoFactory;
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
		
		Map<Integer, String> fabricantesMap = fabricantes.stream()
				.collect(Collectors.toMap(Fabricante::getId, Fabricante::getNome));
		ProdutoQuimicoBase.setFabricantesMap(fabricantesMap);
		 
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
	        
	        Integer fabricanteId = i < fabricantes.size()
	        		? fabricantes.get(i).getId()
	        		: fabricantes.getLast().getId();

	        TipoProduto tipoProduto = TipoProduto.valueOf(campos[0].trim());
	        String nomeComercial = campos[1].trim();
	        String registroAnvisa = campos[2].trim();
	        LocalDate validadeRegistro = LocalDate.parse(campos[3].trim());
	        FormaFarmaceutica formaFarmaceutica = FormaFarmaceutica.valueOf(campos[4].trim());
	        PrincipioAtivo principioAtivo = PrincipioAtivo.valueOf(campos[5].trim());
	        double concentracao = Double.parseDouble(campos[6].trim());
	        Diluente diluente = Diluente.valueOf(campos[7].trim());
	        
	        var produto = ProdutoQuimicoFactory.criarProdutoPorTipo(fabricanteId, tipoProduto, nomeComercial, registroAnvisa, 
	        		validadeRegistro, formaFarmaceutica, principioAtivo, concentracao, diluente);
	        
	        produtoQuimicoService.incluir(produto);
	        i++;
	    }
	    
	    leitor.close();
	}
}