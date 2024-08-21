package com.leonardorossi.librarify.application.loan.usecase;

import com.leonardorossi.librarify.application.book.usecase.FindOneBookUseCase;
import com.leonardorossi.librarify.application.loan.gateways.LoanRepository;
import com.leonardorossi.librarify.application.user.usecase.FindOneUserUseCase;
import com.leonardorossi.librarify.domain.book.entity.Book;
import com.leonardorossi.librarify.domain.loan.entity.Loan;
import com.leonardorossi.librarify.domain.loan.enums.LoanStatusEnum;
import com.leonardorossi.librarify.domain.user.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CheckOutOneBookUseCaseTest {
  @Mock
  private LoanRepository loanRepository;
  
  @Mock
  private FindOneBookUseCase findOneBookUseCase;
  
  @Mock
  private FindOneUserUseCase findOneUserUseCase;
  
  @InjectMocks
  private CheckOutOneBookUseCase checkOutOneBookUseCase;
  
  @Test
  void shouldCreateLoanSuccessfully() {
    User mockUser = User.builder()
      .id(1L)
      .build();
    
    Book mockBook = Book.builder()
      .id(1L)
      .build();
    
    Loan mockLoan = Loan.builder()
      .user(mockUser)
      .book(mockBook)
      .loanDate(LocalDate.now())
      .returnDate(LocalDate.now().plusDays(7))
      .loanStatus(LoanStatusEnum.PENDING)
      .build();
    
    when(findOneUserUseCase.findById(1L)).thenReturn(mockUser);
    when(findOneBookUseCase.execute(1L)).thenReturn(mockBook);
    when(loanRepository.save(any(Loan.class))).thenReturn(mockLoan);
    
    Loan result = checkOutOneBookUseCase.execute(mockLoan);
    
    assertEquals(mockLoan, result);
    verify(loanRepository, times(1)).save(any(Loan.class));
    verify(findOneUserUseCase, times(1)).findById(1L);
    verify(findOneBookUseCase, times(1)).execute(1L);
  }
}
