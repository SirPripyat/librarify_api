package com.leonardorossi.librarify.application.book.usecase;

import com.leonardorossi.librarify.application.book.gateways.BookRepository;
import com.leonardorossi.librarify.domain.book.entity.Book;
import com.leonardorossi.librarify.infra.exception.CustomBadRequestException;
import com.leonardorossi.librarify.presentation.book.messages.BookExceptionMessages;

/**
 * Caso de uso responsável por criar um novo livro.
 */
public class CreateBookUseCase {
  private final BookRepository bookRepository;
  
  public CreateBookUseCase(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }
  
  /**
   * Cria um novo livro.
   *
   * @param book a entidade do livro a ser criada
   * @return a entidade do livro salva
   */
  public Book create(Book book) {
    if (bookRepository.existsByIsbn(book.getIsbn())) {
      throw new CustomBadRequestException(
        String.format(BookExceptionMessages.ISBN_ALREADY_EXISTS, book.getIsbn())
      );
    }
    
    return bookRepository.save(book);
  }
  
}
