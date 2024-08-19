package com.leonardorossi.librarify.application.book.usecase;

import com.leonardorossi.librarify.application.book.gateways.BookRepository;
import com.leonardorossi.librarify.domain.book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Caso de uso para obter os detalhes de todos os livros de maneira paginada.
 */
public class FindAllBookUseCase {
  private final BookRepository bookRepository;
  
  public FindAllBookUseCase(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }
  
  public Page<Book> findAll(Pageable pageable) {
    return bookRepository.findAll(pageable);
  }
}
