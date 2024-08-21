package com.leonardorossi.librarify.presentation.book.messages;

/**
 * Classe que contém as mensagens de exceção específicas para o contexto de
 * livro.
 */
public class BookExceptionMessages {
  public static final String ISBN_ALREADY_EXISTS = "Book with isbn: %s already exists.";
  
  public static final String BOOK_NOT_FOUND = "Book with id: %s not found.";
  
  public static final String PAGINATED_BOOKS_NOT_FOUND = "No books found.";
  
  private BookExceptionMessages() {
  }
}
