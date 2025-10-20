package br.edu.infnet.matheus_mendes_api.exceptions;

import java.time.LocalDateTime;

public record RespostaErro(
        LocalDateTime dataHora,
        int status,
        String erro,
        String mensagem,
        String caminho
) { }