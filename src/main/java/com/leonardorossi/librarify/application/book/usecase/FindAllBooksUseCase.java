package com.leonardorossi.librarify.application.book.usecase;

import com.leonardorossi.librarify.application.book.gateways.BookRepository;
import com.leonardorossi.librarify.domain.book.entity.Book;
import com.leonardorossi.librarify.infra.exception.CustomBadRequestException;
import com.leonardorossi.librarify.presentation.book.messages.BookExceptionMessages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Caso de uso para obter os detalhes de todos os livros de maneira paginada.
 */
public class FindAllBooksUseCase {
  private final BookRepository bookRepository;
  
  /**
   * Construtor para a classe FindAllBooksUseCase.
   *
   * @param bookRepository o repositório responsável por buscar os livros de maneira paginada
   */
  public FindAllBooksUseCase(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }
  
  /**
   * Busca todos os livros de forma paginada.
   *
   * @param pageable as informações de paginação
   * @return uma página contendo as entidades de livros
   */
  public Page<Book> execute(Pageable pageable) {
    Page<Book> books = bookRepository.findAll(pageable);
    validateBookDoesNotExist(books);
    return bookRepository.findAll(pageable);
  }
  
  /**
   * Valida se a página de livros está vazia.
   *
   * @param books a página de livros
   *
   */
  private void validateBookDoesNotExist(Page<Book> books) {
    if (books.isEmpty()) {
      throw new CustomBadRequestException(BookExceptionMessages.PAGINATED_BOOKS_NOT_FOUND);
    }
  }
}
