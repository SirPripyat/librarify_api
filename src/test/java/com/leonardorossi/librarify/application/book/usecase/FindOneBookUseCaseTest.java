package com.leonardorossi.librarify.application.book.usecase;

import com.leonardorossi.librarify.application.book.gateways.BookRepository;
import com.leonardorossi.librarify.domain.book.entity.Book;
import com.leonardorossi.librarify.infra.exception.CustomBadRequestException;
import com.leonardorossi.librarify.presentation.book.messages.BookExceptionMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindOneBookUseCaseTest {
  
  @Mock
  private BookRepository bookRepository;
  
  @InjectMocks
  private FindOneBookUseCase findOneBookUseCase;
  
  private Book mockBook;
  
  @BeforeEach
  void setUp() {
    mockBook = Book.builder()
      .id(1L)
      .title("The Lord of the Rings")
      .author("J.R.R. Tolkien")
      .build();
  }
  
  @Test
  void shouldReturnAValidBookWhenIdExists() {
    long idBook = 1L;
    
    when(bookRepository.findOneById(idBook)).thenReturn(Optional.of(mockBook));
    
    Book result = findOneBookUseCase.find(idBook);
    
    assertEquals(mockBook, result);
  }
  
  @Test
  void shouldThrowExceptionWhenIdDoesNotExist() {
    long idBook = 1L;
    
    when(bookRepository.findOneById(idBook)).thenReturn(Optional.empty());
    
    CustomBadRequestException exception = assertThrows(CustomBadRequestException.class, () ->
      findOneBookUseCase.find(idBook)
    );
    
    assertEquals(
      String.format(BookExceptionMessages.BOOK_NOT_FOUND, idBook), exception.getMessage()
    );
  }
  
}
