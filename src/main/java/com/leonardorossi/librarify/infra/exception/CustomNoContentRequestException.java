package com.leonardorossi.librarify.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção personalizada para requisições sem conteúdo (HTTP 204).
 * Quando lançada, retorna um status HTTP 400 (Bad Request) com a mensagem fornecida.
 */
@ResponseStatus(HttpStatus.NO_CONTENT)
public class CustomNoContentRequestException extends RuntimeException {
  public CustomNoContentRequestException(String message) {
    super(message);
  }
}
