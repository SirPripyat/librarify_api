package com.leonardorossi.librarify.application.loan.gateways;

import com.leonardorossi.librarify.domain.book.entity.Book;
import com.leonardorossi.librarify.domain.loan.entity.Loan;
import java.util.List;
import java.util.Optional;

/**
 * Interface que define o contrato do repositório de empréstimos.
 * Implementações desta interface devem lidar com operações relacionadas a empréstimos.
 */
public interface LoanRepository {
  Loan save(Loan loan);
  
  Optional<Loan> findOneById(Long id);
  
  Optional<List<Book>> findLastBooksCheckOutByUser(Long userId, int limit);
}
