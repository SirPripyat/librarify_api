package com.leonardorossi.librarify.infra.loan.persistence.repository;

import com.leonardorossi.librarify.infra.book.persistence.BookEntity;
import com.leonardorossi.librarify.infra.loan.persistence.LoanEntity;
import java.util.List;
import java.util.Optional;

/**
 * Repositório JPA para realizar operações de persistência na entidade {@link LoanEntity}.
 */
public interface LoanCriteriaBuilderRepository {
  Optional<List<BookEntity>> findLastBooksCheckOutByUser(Long userId, int limit);
}
