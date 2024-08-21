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
   * Busca um livro pelo ID.
   *
   * @param id o ID do livro a ser buscado
   * @return o livro encontrado
   * @throws CustomBadRequestException se o livro não for encontrado
   */
  public Book execute(Long id) {
    return bookRepository.findOneById(id).orElseThrow(() -> bookNotFoundException(id));
  }
  
  /**
   * Cria uma exceção para quando um livro não for encontrado.
   *
   * @param id o ID do livro não encontrado
   * @return a exceção customizada
   */
  private CustomBadRequestException bookNotFoundException(Long id) {
    return new CustomBadRequestException(
      String.format(BookExceptionMessages.BOOK_NOT_FOUND, id)
    );
  }
}
