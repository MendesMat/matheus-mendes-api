package br.edu.infnet.matheus_mendes_api.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class TratadorDeExcecoes {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespostaErro> tratarExcecaoGenerica(Exception ex, WebRequest request) {
        var error = new RespostaErro(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                ex.getMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(ExcecaoRecursoNaoEncontrado.class)
    public ResponseEntity<RespostaErro> tratarRecursoNaoEncontrado(ExcecaoRecursoNaoEncontrado ex, WebRequest request) {
        var error = new RespostaErro(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Recurso não encontrado",
                ex.getMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespostaErro> tratarErroDeValidacao(MethodArgumentNotValidException ex, WebRequest request) {
        var mensagem = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .reduce((a, b) -> a + "; " + b)
                .orElse("Erro de validação desconhecido");

        var error = new RespostaErro(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação",
                mensagem,
                request.getDescription(false)
                
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}