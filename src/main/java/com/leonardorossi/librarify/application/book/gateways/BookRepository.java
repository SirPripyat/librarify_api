package com.leonardorossi.librarify.application.book.gateways;

import com.leonardorossi.librarify.domain.book.entity.Book;

/**
 * Interface que define o contrato do repositório de livros.
 * Implementações desta interface devem lidar com operações relacionadas a livros.
 */
public interface BookRepository {
  Book save(Book book);
  
  boolean existsByIsbn(String isbn);
}
