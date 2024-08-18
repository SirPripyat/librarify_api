package com.leonardorossi.librarify.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção personalizada para erros de requisição inválida (HTTP 400).
 * Quando lançada, retorna um status HTTP 400 (Bad Request) com a mensagem fornecida.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomBadRequestException extends RuntimeException {
  public CustomBadRequestException(String message) {
    super(message);
  }
}
