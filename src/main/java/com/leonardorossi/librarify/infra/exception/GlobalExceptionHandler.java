package com.leonardorossi.librarify.infra.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe responsável por tratar globalmente as exceções na aplicação.
 * Este controlador de exceções captura e trata exceções específicas, retornando uma resposta
 * apropriada com status HTTP.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
  
  /**
   * Trata exceções de validação (MethodArgumentNotValidException).
   *
   * <p>Captura erros de validação em campos de formulário e retorna um mapa contendo os campos
   * inválidos e suas mensagens de erro.</p>
   *
   * @param ex Exceção lançada quando um argumento da função não é válido.
   * @return Um {@link ResponseEntity} contendo o mapa de erros e o status HTTP 400 (Bad Request).
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>>
      handleValidationExceptions(MethodArgumentNotValidException ex) {
    
    Map<String, String> errors = new HashMap<>();
    
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.put(error.getField(), error.getDefaultMessage());
    }
    
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
  
  /**
   * Trata a exceção {@link CustomBadRequestException}.
   *
   * <p>Captura a exceção personalizada e retorna uma resposta com a mensagem do erro.</p>
   *
   * @param ex Exceção personalizada para erros de requisição inválida.
   * @return Um {@link ResponseEntity} contendo a mensagem do erro e o status HTTP 400.
   */
  @ExceptionHandler(CustomBadRequestException.class)
  public ResponseEntity<Map<String, String>>
      handleCustomBadRequestException(CustomBadRequestException ex) {
    Map<String, String> errorResponse = new HashMap<>();
    
    errorResponse.put("message", ex.getMessage());
    
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}
