package com.leonardorossi.librarify.application.loan.usecase;

import com.leonardorossi.librarify.application.loan.gateways.LoanRepository;
import com.leonardorossi.librarify.domain.loan.entity.Loan;
import com.leonardorossi.librarify.infra.exception.CustomBadRequestException;
import com.leonardorossi.librarify.infra.exception.CustomNoContentRequestException;
import com.leonardorossi.librarify.presentation.loan.messages.LoanExceptionMessages;

/**
 * Caso de uso para obter os detalhes de um único empréstimo.
 */
public class FindOneLoanUseCase {
  private final LoanRepository loanRepository;
  
  /**
   * Construtor para a classe FindOneLoanUseCase.
   *
   * @param loanRepository o repositório responsável por buscar o empréstimo pelo ID
   */
  public FindOneLoanUseCase(LoanRepository loanRepository) {
    this.loanRepository = loanRepository;
  }
  
  /**
   * Busca um empréstimo pelo ID.
   *
   * @param id o ID do empréstimo
   * @return o empréstimo encontrado
   * @throws CustomBadRequestException se o empréstimo não for encontrado
   */
  public Loan find(Long id) {
    return loanRepository.findOneById(id).orElseThrow(() -> createLoanNotFoundException(id));
  }
  
  /**
   * Cria uma exceção personalizada quando o empréstimo não é encontrado.
   *
   * @param id o ID do empréstimo
   * @return uma exceção personalizada com a mensagem apropriada
   */
  private CustomNoContentRequestException createLoanNotFoundException(Long id) {
    String errorMessage = String.format(LoanExceptionMessages.LOAN_NOT_FOUND, id);
    return new CustomNoContentRequestException(errorMessage);
  }
}
