package com.leonardorossi.librarify.application.loan.usecase;

import com.leonardorossi.librarify.application.loan.gateways.LoanRepository;
import com.leonardorossi.librarify.domain.loan.entity.Loan;
import com.leonardorossi.librarify.infra.exception.CustomBadRequestException;
import com.leonardorossi.librarify.presentation.loan.messages.LoanExceptionMessages;

/**
 * Caso de uso para obter os detalhes de um único empréstimo.
 */
public class FindOneLoanUseCase {
  private final LoanRepository loanRepository;
  
  public FindOneLoanUseCase(LoanRepository loanRepository) {
    this.loanRepository = loanRepository;
  }
  
  /**
   * Busca um empréstimo pelo id.
   */
  public Loan find(Long id) {
    return loanRepository.findOneById(id).orElseThrow(() ->
      new CustomBadRequestException(
        String.format(LoanExceptionMessages.LOAN_NOT_FOUND, id)
      ));
  }
}
