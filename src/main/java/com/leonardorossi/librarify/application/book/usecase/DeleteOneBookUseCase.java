package com.leonardorossi.librarify.application.book.usecase;

import com.leonardorossi.librarify.application.book.gateways.BookRepository;
import com.leonardorossi.librarify.domain.book.entity.Book;

/**
 * Caso de uso responsável por deletar um livro, caso ele exista.
 */
public class DeleteOneBookUseCase {
  
  private final BookRepository bookRepository;
  private final FindOneBookUseCase findOneBookUseCase;
  
  /**
   * Construtor para a classe DeleteOneBookUseCase.
   *
   * @param bookRepository    o repositório responsável por salvar o estado atualizado do livro
   * @param findOneBookUseCase o caso de uso responsável por buscar o livro pelo ID
   */
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
  public Book execute(Long id) {
    Book book = findOneBookUseCase.execute(id);
    toggleStatus(book);
    return bookRepository.save(book);
  }
  
  /**
   * Altera o status de um livro.
   *
   * @param book a entidade de livro que o status será alterado.
   */
  private void toggleStatus(Book book) {
    book.setStatus(!book.getStatus());
  }
}
