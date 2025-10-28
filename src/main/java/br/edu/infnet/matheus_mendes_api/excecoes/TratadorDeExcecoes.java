package br.edu.infnet.matheus_mendes_api.excecoes;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(ExcecaoRecursoDuplicado.class)
    public ResponseEntity<RespostaDeErro> tratarRecursoDuplicado(
            ExcecaoRecursoDuplicado ex, WebRequest request) {

        var erro = new RespostaDeErro(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Recurso duplicado",
                ex.getMessage(),
                request.getDescription(false)
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarErrosDeValidacao(MethodArgumentNotValidException ex) {
        
    	Map<String, Object> corpo = new HashMap<>();
        
        corpo.put("timestamp", LocalDateTime.now());
        corpo.put("status", HttpStatus.BAD_REQUEST.value());
        corpo.put("error", "Erro de validação");

        Map<String, String> erros = new HashMap<>();
        
        ex.getBindingResult().getFieldErrors()
                .forEach(field -> erros.put(field.getField(), field.getDefaultMessage()));
        corpo.put("messages", erros);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(corpo);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<RespostaDeErro> tratarViolacaoDeIntegridade(
            DataIntegrityViolationException ex, WebRequest request) {

        var erro = new RespostaDeErro(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Violação de integridade de dados",
                "Ocorreu uma violação de restrição no banco de dados.",
                request.getDescription(false)
        );
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespostaDeErro> tratarExcecaoGeral(Exception ex, WebRequest request) {
        
    	var erro = new RespostaDeErro(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno do servidor",
                ex.getMessage(),
                request.getDescription(false)
        );
    	
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}