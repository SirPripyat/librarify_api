package com.leonardorossi.librarify.infra.recommendation.persistence;

import com.leonardorossi.librarify.infra.book.persistence.BookEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface responsável por gerenciar as operações de persistência de recomendações.
 */
public interface RecommendationJpaRepository extends JpaRepository<BookEntity, Long> {
  List<BookEntity> findAllByIdIsNotInAndCategory(List<Long> borrowedBooks, String category);
}
