package com.leonardorossi.librarify.application.book.usecase;

import com.leonardorossi.librarify.application.book.gateways.BookRepository;
import com.leonardorossi.librarify.domain.book.entity.Book;

/**
 * Caso de uso responsável por deletar um livro, caso ele exista.
 */
public class DeleteOneBookUseCase {
  
  private final BookRepository bookRepository;
  private final FindOneBookUseCase findOneBookUseCase;
  
  public DeleteOneBookUseCase(
      BookRepository bookRepository,
      FindOneBookUseCase findOneBookUseCase
  ) {
    this.bookRepository = bookRepository;
    this.findOneBookUseCase = findOneBookUseCase;
  }
  
  /**
   * Deleta um livro se ele existir.
   *
   * @param id o ID do livro a ser deletado.
   */
  public Book toggleStatus(Long id) {
    Book book = findOneBookUseCase.find(id);
    book.setStatus(!book.getStatus());
    return bookRepository.save(book);
  }
}
