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

import br.edu.infnet.matheus_mendes_api.controladores.dto.ProdutoQuimicoDto;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.Fabricante;
import br.edu.infnet.matheus_mendes_api.modelo.dominio.enums.*;
import br.edu.infnet.matheus_mendes_api.modelo.servicos.ServicoProdutoQuimico;

@Component
@Order(2)
public class ProdutoQuimicoLoader implements ApplicationRunner {

    private final ServicoProdutoQuimico produtoQuimicoService;
    private final FabricanteLoader fabricanteLoader;

    public ProdutoQuimicoLoader(ServicoProdutoQuimico produtoQuimicoService, FabricanteLoader fabricanteLoader) {
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
        var leitor = new BufferedReader(new FileReader(caminhoArquivo));
        var linhaAtual = new String[1];

        var linhaPreenchida = atualizarLinha(leitor, linhaAtual);
        var i = 0;

        while (linhaPreenchida) {
        	var linha = linhaAtual[0];
        	
            if (linhaValida(linha)) {
                Fabricante fabricante = escolherFabricante(i, fabricantes);
                ProdutoQuimicoDto dto = criarDto(linha, fabricante);
                produtoQuimicoService.incluir(dto);
                i++;
            }

            linhaPreenchida = atualizarLinha(leitor, linhaAtual);
        }

        leitor.close();
    }
    
    private boolean atualizarLinha(BufferedReader leitor, String[] linhaHolder) throws IOException {
        linhaHolder[0] = leitor.readLine();
        return linhaHolder[0] != null;
    }

    private boolean linhaValida(String linha) {
        String[] campos = linha.split(",");
        return campos.length >= 8;
    }

    private Fabricante escolherFabricante(int index, List<Fabricante> fabricantes) {
        return index < fabricantes.size()
                ? fabricantes.get(index)
                : fabricantes.get(fabricantes.size() - 1);
    }

    private ProdutoQuimicoDto criarDto(String linha, Fabricante fabricante) {
        String[] campos = linha.split(",");

        return new ProdutoQuimicoDto(
                null,
                fabricante,
                TipoProduto.valueOf(campos[0].trim()),
                campos[1].trim(),
                campos[2].trim(),
                LocalDate.parse(campos[3].trim()),
                true,
                FormaFarmaceutica.valueOf(campos[4].trim()),
                PrincipioAtivo.valueOf(campos[5].trim()),
                Double.parseDouble(campos[6].trim()),
                Diluente.valueOf(campos[7].trim())
        );
    }
}