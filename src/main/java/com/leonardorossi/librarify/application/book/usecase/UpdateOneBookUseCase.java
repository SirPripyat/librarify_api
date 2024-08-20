package com.leonardorossi.librarify.application.book.usecase;

import com.leonardorossi.librarify.application.book.gateways.BookRepository;
import com.leonardorossi.librarify.domain.book.entity.Book;

/**
 * Caso de uso para atualizar os detalhes de um único livro.
 */
public class UpdateOneBookUseCase {
  private final BookRepository bookRepository;
  private final FindOneBookUseCase findOneBookUseCase;
  
  public UpdateOneBookUseCase(
      BookRepository bookRepository,
      FindOneBookUseCase findOneBookUseCase1
  ) {
    this.bookRepository = bookRepository;
    this.findOneBookUseCase = findOneBookUseCase1;
  }
  
  /**
   * Atualiza os detalhes de um único livro.
   *
   * @param id o id do livro a ser atualizado
   * @param book a entidade do livro a ser atualizado
   * @return a entidade do livro atualizada
   */
  public Book update(Long id, Book book) {
    Book bookToUpdate = findOneBookUseCase.find(id);
    
    updateFields(bookToUpdate, book);
    
    return bookRepository.save(bookToUpdate);
  }
  
  private void updateFields(Book bookToUpdate, Book book) {
    if (book.getTitle() != null) {
      bookToUpdate.setTitle(book.getTitle());
    }
    
    if (book.getAuthor() != null) {
      bookToUpdate.setAuthor(book.getAuthor());
    }
    
    if (book.getCategory() != null) {
      bookToUpdate.setCategory(book.getCategory());
    }
  }
}
