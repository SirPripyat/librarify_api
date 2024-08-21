package com.leonardorossi.librarify.application.loan.usecase;

import com.leonardorossi.librarify.application.loan.gateways.LoanRepository;
import com.leonardorossi.librarify.domain.loan.entity.Loan;
import com.leonardorossi.librarify.domain.loan.enums.LoanStatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateOneLoanUseCaseTest {
  
  @Mock
  private LoanRepository loanRepository;
  
  @Mock
  private FindOneLoanUseCase findOneLoanUseCase;
  
  @InjectMocks
  private UpdateOneLoanUseCase updateOneLoanUseCase;
  
  @Test
  void shouldUpdateLoanWithNewReturnDate() {
    Long loanId = 1L;
    
    Loan existingLoan = Loan.builder()
      .id(loanId)
      .loanStatus(LoanStatusEnum.PENDING)
      .returnDate(LocalDate.of(2024, 8, 30))
      .build();
    
    Loan updatedLoan = Loan.builder()
      .loanStatus(LoanStatusEnum.RETURNED)
      .returnDate(LocalDate.of(2024, 8, 25))
      .build();
    
    when(findOneLoanUseCase.find(loanId)).thenReturn(existingLoan);
    when(loanRepository.save(existingLoan)).thenReturn(existingLoan);
    
    Loan result = updateOneLoanUseCase.update(loanId, updatedLoan);
    
    verify(findOneLoanUseCase, times(1)).find(loanId);
    verify(loanRepository, times(1)).save(existingLoan);
    
    assertNotNull(result);
    assertEquals(LoanStatusEnum.RETURNED, result.getLoanStatus());
    assertEquals(LocalDate.of(2024, 8, 25), result.getReturnDate());
  }
  
  @Test
  void shouldUpdateLoanStatusToLateIfReturnDateIsPassed() {
    Long loanId = 1L;
    
    Loan existingLoan = Loan.builder()
      .id(loanId)
      .loanStatus(LoanStatusEnum.PENDING)
      .returnDate(LocalDate.now().minusDays(5))
      .build();
    
    Loan updatedLoan = Loan.builder()
      .loanStatus(LoanStatusEnum.PENDING)
      .build();
    
    when(findOneLoanUseCase.find(loanId)).thenReturn(existingLoan);
    when(loanRepository.save(existingLoan)).thenReturn(existingLoan);
    
    Loan result = updateOneLoanUseCase.update(loanId, updatedLoan);
    
    verify(findOneLoanUseCase, times(1)).find(loanId);
    verify(loanRepository, times(1)).save(existingLoan);
    
    assertNotNull(result);
    assertEquals(LoanStatusEnum.LATE, result.getLoanStatus());
  }
  
  @Test
  void shouldNotUpdateLoanStatusIfNullInUpdatedLoan() {
    Long loanId = 1L;
    
    Loan existingLoan = Loan.builder()
      .id(loanId)
      .loanStatus(LoanStatusEnum.PENDING)
      .returnDate(LocalDate.of(2024, 8, 30))
      .build();
    
    Loan updatedLoan = Loan.builder().build(); // Loan atualizado sem status
    
    when(findOneLoanUseCase.find(loanId)).thenReturn(existingLoan);
    when(loanRepository.save(existingLoan)).thenReturn(existingLoan);
    
    Loan result = updateOneLoanUseCase.update(loanId, updatedLoan);
    
    verify(findOneLoanUseCase, times(1)).find(loanId);
    verify(loanRepository, times(1)).save(existingLoan);
    
    assertNotNull(result);
    assertEquals(LoanStatusEnum.PENDING, result.getLoanStatus()); // O status deve permanecer inalterado
    assertEquals(LocalDate.of(2024, 8, 30), result.getReturnDate());
  }
}
