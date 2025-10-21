package br.edu.infnet.matheus_mendes_api.excecoes;

import java.time.LocalDateTime;

public record RespostaDeErro(
        LocalDateTime dataHora,
        int status,
        String erro,
        String mensagem,
        String caminho
) { }