package com.leonardorossi.librarify.application.book.usecase;

import com.leonardorossi.librarify.application.book.gateways.BookRepository;
import com.leonardorossi.librarify.domain.book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Caso de uso para obter os detalhes de todos os livros de maneira paginada.
 */
public class FindAllBooksUseCase {
  private final BookRepository bookRepository;
  
  public FindAllBooksUseCase(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }
  
  /**
   * Busca todos os livros de forma paginada.
   *
   * @param pageable as informações de paginação
   * @return uma página contendo as entidades de livros
   */
  public Page<Book> findAll(Pageable pageable) {
    return bookRepository.findAll(pageable);
  }
}
