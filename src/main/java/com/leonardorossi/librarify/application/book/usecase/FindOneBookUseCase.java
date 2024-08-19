package com.leonardorossi.librarify.application.book.usecase;

import com.leonardorossi.librarify.application.book.gateways.BookRepository;
import com.leonardorossi.librarify.domain.book.entity.Book;
import com.leonardorossi.librarify.infra.exception.CustomBadRequestException;
import com.leonardorossi.librarify.presentation.book.messages.BookExceptionMessages;

/**
 * Caso de uso para obter os detalhes de um único livro.
 */
public class FindOneBookUseCase {
  
  private final BookRepository bookRepository;
  
  public FindOneBookUseCase(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }
  
  /**
   * Busca um livro pelo id.
   */
  public Book find(Long id) {
    return bookRepository.findOneById(id).orElseThrow(() ->
      new CustomBadRequestException(
        String.format(BookExceptionMessages.BOOK_NOT_FOUND, id)
      ));
  }
}
