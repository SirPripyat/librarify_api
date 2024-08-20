package com.leonardorossi.librarify.application.book.usecase;

import com.leonardorossi.librarify.application.book.gateways.BookRepository;
import com.leonardorossi.librarify.domain.book.entity.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteOneBookUseCaseTest {

  @Mock
  private BookRepository bookRepository;
  
  @InjectMocks
  private FindOneBookUseCase findOneBookUseCase;
  
  @InjectMocks
  private DeleteOneBookUseCase deleteOneBookUseCase;
  
  @Test
  void shouldToggleUserStatusToFalse() {
    Long idBook = 1L;
    Book existingBook = Book.builder()
      .id(idBook)
      .status(true)
      .build();
    
    when(findOneBookUseCase.find(idBook)).thenReturn(existingBook);
    when(bookRepository.save(existingBook)).thenReturn(existingBook);
    
    Book updatedBook = deleteOneBookUseCase.toggleStatus(idBook);
    
    assertEquals(false, updatedBook.getStatus());
    verify(bookRepository, times(1)).save(existingBook);
  }
  
  @Test
  void shouldToggleUserStatusToTrue() {
    Long idBook = 1L;
    Book existingBook = Book.builder()
      .id(idBook)
      .status(false)
      .build();
    
    when(findOneBookUseCase.find(idBook)).thenReturn(existingBook);
    when(bookRepository.save(existingBook)).thenReturn(existingBook);
    
    Book updatedBook = deleteOneBookUseCase.toggleStatus(idBook);
    
    assertEquals(true, updatedBook.getStatus());
    verify(bookRepository, times(1)).save(existingBook);
  }
  
  
}
