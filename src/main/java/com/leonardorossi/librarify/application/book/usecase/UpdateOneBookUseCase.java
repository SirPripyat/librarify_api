package com.leonardorossi.librarify.application.book.usecase;

import com.leonardorossi.librarify.application.book.gateways.BookRepository;
import com.leonardorossi.librarify.domain.book.entity.Book;

/**
 * Caso de uso para atualizar os detalhes de um único livro.
 */
public class UpdateOneBookUseCase {
  private final BookRepository bookRepository;
  private final FindOneBookUseCase findOneBookUseCase;
  
  /**
   * Construtor para a classe UpdateOneBookUseCase.
   *
   * @param bookRepository    o repositório responsável por salvar o livro atualizado
   * @param findOneBookUseCase o caso de uso responsável por buscar o livro pelo ID antes de
   *                           atualizá-lo
   */
  public UpdateOneBookUseCase(
      BookRepository bookRepository,
      FindOneBookUseCase findOneBookUseCase
  ) {
    this.bookRepository = bookRepository;
    this.findOneBookUseCase = findOneBookUseCase;
  }
  
  /**
   * Atualiza os detalhes de um único livro.
   *
   * @param id o id do livro a ser atualizado
   * @param book a entidade do livro a ser atualizado
   * @return a entidade do livro atualizada
   */
  public Book execute(Long id, Book book) {
    Book bookToUpdate = findOneBookUseCase.execute(id);
    
    updateBookDetails(bookToUpdate, book);
    
    return bookRepository.save(bookToUpdate);
  }
  
  /**
   * Atualiza os campos do livro existente com os novos valores fornecidos.
   *
   * @param bookToUpdate o livro existente a ser atualizado
   * @param updatedBook o livro com os novos detalhes
   */
  private void updateBookDetails(Book bookToUpdate, Book updatedBook) {
    if (updatedBook.getTitle() != null) {
      bookToUpdate.setTitle(updatedBook.getTitle());
    }
    
    if (updatedBook.getAuthor() != null) {
      bookToUpdate.setAuthor(updatedBook.getAuthor());
    }
    
    if (updatedBook.getCategory() != null) {
      bookToUpdate.setCategory(updatedBook.getCategory());
    }
  }
}
