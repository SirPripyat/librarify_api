package com.leonardorossi.librarify.application.loan.gateways;

import com.leonardorossi.librarify.domain.loan.entity.Loan;

/**
 * Interface que define o contrato do repositório de empréstimos.
 * Implementações desta interface devem lidar com operações relacionadas a empréstimos.
 */
public interface LoanRepository {
  Loan save(Loan loan);
}
