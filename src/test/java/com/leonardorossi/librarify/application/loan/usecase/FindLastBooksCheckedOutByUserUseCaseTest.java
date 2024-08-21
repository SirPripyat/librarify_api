package com.leonardorossi.librarify.application.loan.usecase;

import com.leonardorossi.librarify.application.loan.gateways.LoanRepository;
import com.leonardorossi.librarify.domain.book.entity.Book;
import com.leonardorossi.librarify.infra.exception.CustomBadRequestException;
import com.leonardorossi.librarify.presentation.loan.messages.LoanExceptionMessages;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FindLastBooksCheckedOutByUserUseCaseTest {
  
  @Mock
  private LoanRepository loanRepository;
  
  @InjectMocks
  private FindLastBooksCheckedOutByUserUseCase findLastBooksCheckedOutByUserUseCase;
  
  
  @Test
  void shouldReturnBooksWhenUserHasLoans() {
    Long userId = 1L;
    
    List<Book> books = List.of(
      Book.builder().title("Bible").build(),
      Book.builder().title("Harry Potter").build(),
      Book.builder().title("The Lord of the Rings").build()
    );
    
    when(loanRepository.findLastBooksCheckOutByUser(userId)).thenReturn(Optional.of(books));
    
    List<Book> result = findLastBooksCheckedOutByUserUseCase.find(userId);
    
    assertNotNull(result);
    assertEquals(3, result.size());
  }
  
  @Test
  void shouldThrowExceptionWhenUserHasNoLoans() {
    Long userId = 1L;
    
    when(loanRepository.findLastBooksCheckOutByUser(userId)).thenReturn(Optional.empty());
    
    CustomBadRequestException exception = assertThrows(CustomBadRequestException.class,
      () -> findLastBooksCheckedOutByUserUseCase.find(userId));
    
    assertEquals(
      String.format(LoanExceptionMessages.USER_DONT_HAVE_LOANS, userId), exception.getMessage()
    );
  }
}
