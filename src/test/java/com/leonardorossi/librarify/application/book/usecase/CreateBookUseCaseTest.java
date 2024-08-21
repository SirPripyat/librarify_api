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

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateBookUseCaseTest {
  
  @Mock
  private BookRepository bookRepository;
  
  @InjectMocks
  private CreateBookUseCase createBookUseCase;
  
  private Book validBook;
  
  @BeforeEach
  void setUp() {
    validBook = Book.builder()
      .id(10L)
      .title("O Senhor dos Anéis")
      .author("J.R.R. Tolkien")
      .isbn("9780345538376")
      .publicationDate(LocalDate.of(1954, 7, 29))
      .category("Fantasia")
      .status(true)
      .build();
  }
  
  @Test
  void shouldCreateBook() {
    when(bookRepository.existsByIsbn(validBook.getIsbn())).thenReturn(false);
    when(bookRepository.save(validBook)).thenReturn(validBook);
    
    Book createdBook = createBookUseCase.create(validBook);
    
    assertNotNull(createdBook);
    assertEquals(validBook, createdBook);
    
    verify(bookRepository, times(1)).save(validBook);
    verify(bookRepository, times(1)).save(validBook);
  }
  
  @Test
  void shouldThrowCustomBadRequestExceptionWhenIsbnAlreadyExists() {
    when(bookRepository.existsByIsbn(validBook.getIsbn())).thenReturn(true);
    
    CustomBadRequestException exception = assertThrows(CustomBadRequestException.class, () ->
      createBookUseCase.create(validBook)
    );
    
    assertEquals(
      String.format(BookExceptionMessages.ISBN_ALREADY_EXISTS, validBook.getIsbn()),
      exception.getMessage()
    );
    verify(bookRepository, never()).save(validBook);
  }
  
}
