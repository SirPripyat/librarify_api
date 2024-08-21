package com.leonardorossi.librarify.application.loan.usecase;

import com.leonardorossi.librarify.application.loan.gateways.LoanRepository;
import com.leonardorossi.librarify.infra.exception.CustomNoContentRequestException;
import com.leonardorossi.librarify.presentation.loan.messages.LoanExceptionMessages;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.leonardorossi.librarify.domain.loan.entity.Loan;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FindOneLoanUseCaseTest {
  @Mock
  private LoanRepository loanRepository;
  
  @InjectMocks
  private FindOneLoanUseCase findOneLoanUseCase;
  
  @Test
  void find_ShouldReturnLoan_WhenLoanExists() {
    Long loanId = 1L;
    Loan expectedLoan = new Loan();
    when(loanRepository.findOneById(loanId)).thenReturn(Optional.of(expectedLoan));
    
    Loan result = findOneLoanUseCase.find(loanId);
    
    assertNotNull(result);
    assertEquals(expectedLoan, result);
    verify(loanRepository, times(1)).findOneById(loanId);
  }
  
  @Test
  void find_ShouldThrowCustomBadRequestException_WhenLoanDoesNotExist() {
    Long loanId = 1L;
    when(loanRepository.findOneById(loanId)).thenReturn(Optional.empty());
    
    CustomNoContentRequestException exception = assertThrows(CustomNoContentRequestException.class,
      () -> findOneLoanUseCase.find(loanId));
    
    assertEquals(
      String.format(LoanExceptionMessages.LOAN_NOT_FOUND, loanId), exception.getMessage()
    );
    verify(loanRepository, times(1)).findOneById(loanId);
  }
}
