package com.leonardorossi.librarify.application.book.usecase;

import com.leonardorossi.librarify.application.book.gateways.BookRepository;
import com.leonardorossi.librarify.domain.book.entity.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindAllBooksUseCaseTest {
  
  @Mock
  private BookRepository bookRepository;
  
  @InjectMocks
  private FindAllBooksUseCase findAllBooksUseCase;
  
  @Test
  void findAll_shouldReturnPaginatedUsers() {
    Pageable pageable = PageRequest.of(0, 6);
    List<Book> bookList = List.of(
      Book.builder().id(1L).title("Harry Potter and the Sorcerer's Stone").build(),
      Book.builder().id(2L).title("Harry Potter and the Chamber of Secrets").build(),
      Book.builder().id(3L).title("Harry Potter and the Prisoner of Azkaban").build(),
      Book.builder().id(4L).title("Harry Potter and the Goblet of Fire").build(),
      Book.builder().id(5L).title("Harry Potter and the Order of the Phoenix").build(),
      Book.builder().id(6L).title("Harry Potter and the Half-Blood Prince").build()
    );
    Page<Book> paginatedBooks = new PageImpl<>(bookList, pageable, bookList.size());
    
    when(bookRepository.findAll(pageable)).thenReturn(paginatedBooks);
    
    Page<Book> result = findAllBooksUseCase.findAll(pageable);
    
    assertEquals(paginatedBooks, result);
    assertEquals(6, result.getTotalElements());
    assertEquals(6, result.getContent().size());
    assertEquals("Harry Potter and the Sorcerer's Stone", result.getContent().getFirst().getTitle());
    assertEquals("Harry Potter and the Half-Blood Prince", result.getContent().get(5).getTitle());
  }
}
