package com.leonardorossi.librarify.infra.book.persistence;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

/**
 * Repositório JPA para realizar operações de persistência na entidade {@link BookEntity}.
 * Fornece métodos padrão do Spring Data JPA e consultas personalizadas.
 */
public interface BookJpaRepository extends JpaRepository<BookEntity, Long> {
  boolean existsByIsbn(String isbn);
  
  Optional<BookEntity> findOneById(Long id);
  
  @NonNull
  Page<BookEntity> findAll(@NonNull Pageable pageable);
}
