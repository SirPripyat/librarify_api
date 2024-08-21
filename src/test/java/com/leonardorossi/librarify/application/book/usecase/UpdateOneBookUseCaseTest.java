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
public class UpdateOneBookUseCaseTest {
  
  @Mock
  private BookRepository bookRepository;
  
  @Mock
  private FindOneBookUseCase findOneBookUseCase;
  
  @InjectMocks
  private UpdateOneBookUseCase updateOneBookUseCase;
  
  @Test
  void shouldUpdateBook() {
    Long idBook = 1L;
    
    Book existingBook = Book.builder()
      .title("Harry Potter")
      .author("J.K. Rouling")
      .build();
    
    Book bookToUpdate = Book.builder()
      .title("Harry Potter and the Philosopher's Stone")
      .author("J.K. Rowling")
      .category("Teen")
      .build();
    
    when(findOneBookUseCase.find(idBook)).thenReturn(existingBook);
    when(bookRepository.save(existingBook)).thenReturn(existingBook);
    
    Book updatedBook = updateOneBookUseCase.update(idBook, bookToUpdate);

    assertEquals("Harry Potter and the Philosopher's Stone", updatedBook.getTitle());
    assertEquals("J.K. Rowling", updatedBook.getAuthor());
    
    verify(findOneBookUseCase, times(1)).find(idBook);
    verify(bookRepository, times(1)).save(existingBook);
  }
  
}
