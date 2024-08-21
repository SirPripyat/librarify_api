package com.leonardorossi.librarify.presentation.book.messages;

/**
 * Classe que contém as mensagens de exceção específicas para o contexto de
 * livro.
 */
public class BookExceptionMessages {
  public static final String ISBN_ALREADY_EXISTS = "Livro com isbn: %s já existe.";
  
  public static final String BOOK_NOT_FOUND = "Livro com id: %s não encontrado.";
  
  public static final String PAGINATED_BOOKS_NOT_FOUND = "Nenhum livro encontrado.";
  
  private BookExceptionMessages() {
  }
}
