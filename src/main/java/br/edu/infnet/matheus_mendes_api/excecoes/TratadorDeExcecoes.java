package br.edu.infnet.matheus_mendes_api.excecoes;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class TratadorDeExcecoes {

    @ExceptionHandler(ExcecaoRecursoNaoEncontrado.class)
    public ResponseEntity<RespostaDeErro> tratarRecursoNaoEncontrado(
            ExcecaoRecursoNaoEncontrado ex, WebRequest request) {
        
        var erro = new RespostaDeErro(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                ex.getMessage(),
                request.getDescription(false)
        );
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(ExcecaoRecursoInvalido.class)
    public ResponseEntity<RespostaDeErro> tratarRecursoInvalido(
            ExcecaoRecursoInvalido ex, WebRequest request) {
        
        var erro = new RespostaDeErro(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Requisição inválida",
                ex.getMessage(),
                request.getDescription(false)
        );
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
