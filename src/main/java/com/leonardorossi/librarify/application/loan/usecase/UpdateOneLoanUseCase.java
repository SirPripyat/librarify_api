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
  
  public UpdateOneLoanUseCase(
      LoanRepository loanRepository,
      FindOneLoanUseCase findOneLoanUseCase
  ) {
    this.loanRepository = loanRepository;
    this.findOneLoanUseCase = findOneLoanUseCase;
  }
  
  /**
   * Atualiza um empréstimo.
   */
  public Loan update(Long idLoan, Loan loan) {
    Loan loanToUpdate = findOneLoanUseCase.find(idLoan);
    
    updateFields(loanToUpdate, loan);
    
    return loanRepository.save(loanToUpdate);
  }
  
  private void updateFields(Loan loanToUpdate, Loan loan) {
    updateLoanStatus(loanToUpdate, loan);
    
    if (loan.getReturnDate() != null) {
      loanToUpdate.setReturnDate(loan.getReturnDate());
    }
  }
  
  private void updateLoanStatus(Loan loanToUpdate, Loan loan) {
    if (loan.getLoanStatus() == null) {
      return;
    }
    
    LocalDate currentDate = LocalDate.now();
    LocalDate returnDate = loanToUpdate.getReturnDate();
    
    boolean isLate = returnDate != null
        && currentDate.isAfter(returnDate)
        && loanToUpdate.getLoanStatus() != LoanStatusEnum.RETURNED;
    
    if (isLate) {
      loan.setLoanStatus(LoanStatusEnum.LATE);
    } else {
      loanToUpdate.setLoanStatus(loan.getLoanStatus());
    }
  }
  
}
