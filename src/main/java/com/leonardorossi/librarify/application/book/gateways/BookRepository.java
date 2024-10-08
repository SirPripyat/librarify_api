package com.leonardorossi.librarify.application.book.gateways;

import com.leonardorossi.librarify.domain.book.entity.Book;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface que define o contrato do repositório de livros.
 * Implementações desta interface devem lidar com operações relacionadas a livros.
 */
public interface BookRepository {
  Book save(Book book);
  
  boolean existsByIsbn(String isbn);
  
  Optional<Book> findOneById(Long id);
  
  Page<Book> findAll(Pageable pageable);
}
