package com.leonardorossi.librarify.application.loan.usecase;

import com.leonardorossi.librarify.application.loan.gateways.LoanRepository;
import com.leonardorossi.librarify.domain.loan.entity.Loan;
import com.leonardorossi.librarify.domain.loan.enums.LoanStatusEnum;
import java.time.LocalDate;

/**
 * Caso de uso para atualizar um empréstimo.
 */
public class UpdateOneLoanUseCase {
  private final LoanRepository loanRepository;
  private final FindOneLoanUseCase findOneLoanUseCase;
  
  /**
   * Construtor para a classe UpdateOneLoanUseCase.
   *
   * @param loanRepository o repositório responsável por salvar as atualizações do empréstimo
   * @param findOneLoanUseCase o caso de uso responsável por buscar um empréstimo pelo ID
   */
  public UpdateOneLoanUseCase(
      LoanRepository loanRepository,
      FindOneLoanUseCase findOneLoanUseCase
  ) {
    this.loanRepository = loanRepository;
    this.findOneLoanUseCase = findOneLoanUseCase;
  }
  
  /**
   * Atualiza um empréstimo.
   *
   * @param idLoan o ID do empréstimo a ser atualizado
   * @param loan   o objeto com os novos dados do empréstimo
   * @return o empréstimo atualizado
   */
  public Loan update(Long idLoan, Loan loan) {
    Loan loanToUpdate = findOneLoanUseCase.find(idLoan);
    
    updateFields(loanToUpdate, loan);
    
    return loanRepository.save(loanToUpdate);
  }
  
  /**
   * Atualiza os campos do empréstimo, caso haja informações disponíveis no novo objeto.
   *
   * @param loanToUpdate o empréstimo a ser atualizado
   * @param loan         o objeto com os novos dados
   */
  private void updateFields(Loan loanToUpdate, Loan loan) {
    updateLoanStatus(loanToUpdate, loan);
    updateReturnDate(loanToUpdate, loan);
  }
  
  /**
   * Atualiza o status do empréstimo, considerando a data de retorno e o status atual.
   *
   * @param loanToUpdate o empréstimo a ser atualizado
   * @param loan         o objeto com os novos dados
   */
  private void updateLoanStatus(Loan loanToUpdate, Loan loan) {
    if (loan.getLoanStatus() == null) {
      return;
    }
    
    LocalDate currentDate = LocalDate.now();
    LocalDate returnDate = loanToUpdate.getReturnDate();
    
    boolean isLate = isLoanLate(currentDate, returnDate, loanToUpdate);
    
    loanToUpdate.setLoanStatus(
        isLate ? LoanStatusEnum.LATE : loan.getLoanStatus()
    );
  }
  
  /**
   * Verifica se o empréstimo está atrasado.
   *
   * @param currentDate  a data atual
   * @param returnDate   a data de retorno prevista
   * @param loanToUpdate o empréstimo a ser verificado
   * @return true se o empréstimo estiver atrasado, false caso contrário
   */
  private boolean isLoanLate(LocalDate currentDate, LocalDate returnDate, Loan loanToUpdate) {
    return returnDate != null
        && currentDate.isAfter(returnDate)
        && loanToUpdate.getLoanStatus() != LoanStatusEnum.RETURNED;
  }
  
  /**
   * Atualiza a data de retorno do empréstimo, caso uma nova data seja fornecida.
   *
   * @param loanToUpdate o empréstimo a ser atualizado
   * @param loan         o objeto com os novos dados
   */
  private void updateReturnDate(Loan loanToUpdate, Loan loan) {
    if (loan.getReturnDate() != null) {
      loanToUpdate.setReturnDate(loan.getReturnDate());
    }
  }
}
