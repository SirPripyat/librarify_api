package com.leonardorossi.librarify.application.reccomend.usecase;


import com.leonardorossi.librarify.application.loan.usecase.FindLastBooksCheckedOutByUserUseCase;
import com.leonardorossi.librarify.application.reccomendation.gateways.RecommendationRepository;
import com.leonardorossi.librarify.application.reccomendation.usecase.RecommendBookByCategoryUseCase;
import com.leonardorossi.librarify.domain.book.entity.Book;
import com.leonardorossi.librarify.domain.recommendation.Recommendation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecommendBookByCategoryUseCaseTest {
  
  @Mock
  private RecommendationRepository repository;
  
  @Mock
  private FindLastBooksCheckedOutByUserUseCase findLastBooksCheckedOutByUserUseCase;
  
  @InjectMocks
  private RecommendBookByCategoryUseCase recommendBookByCategoryUseCase;
  
  @Test
  void shouldRecommendBookBasedOnCategory() {
    Long idUser = 1L;
    
    List<Book> bookList = List.of(
      Book.builder().id(1L).title("Harry Potter").category("Fantasy").build(),
      Book.builder().id(2L).title("The Hobbit").category("Fantasy").build(),
      Book.builder().id(3L).title("The Hunger Games").category("Science Fiction").build(),
      Book.builder().id(4L).title("The Maze Runner").category("Science Fiction").build(),
      Book.builder().id(5L).title("The Da Vinci Code").category("Mystery").build(),
      Book.builder().id(6L).title("Angels & Demons").category("Mystery").build(),
      Book.builder().id(7L).title("Inferno").category("Mystery").build()
    );
    
    List<Book> bookListOfRecommendations = List.of(
      Book.builder().title("The Girl with the Dragon Tattoo").category("Mystery").build(),
      Book.builder().title("Gone Girl").category("Mystery").build(),
      Book.builder().title("The Da Vinci Code").category("Mystery").build(),
      Book.builder().title("Murder on the Orient Express").category("Mystery").build(),
      Book.builder().title("The Silent Patient").category("Mystery").build()
    );
    
    Recommendation expectedRecommendation = Recommendation.builder()
      .bookList(bookListOfRecommendations)
      .build();
    
    when(repository.recommendByCategory(anyList(), eq("Mystery"))).thenReturn(expectedRecommendation);
    
    when(findLastBooksCheckedOutByUserUseCase.find(idUser)).thenReturn(bookList);
    
    Recommendation recommendation = recommendBookByCategoryUseCase.execute(idUser);
    
    assertEquals(expectedRecommendation, recommendation);
    verify(repository, times(1)).recommendByCategory(anyList(), eq("Mystery"));
    verify(findLastBooksCheckedOutByUserUseCase, times(1)).find(idUser);
  }
  
  @Test
  void shouldReturnNullWhenNoBooksBorrowed() {
    Long idUser = 1L;
    
    when(findLastBooksCheckedOutByUserUseCase.find(idUser)).thenReturn(Collections.emptyList());
    
    Recommendation recommendation = recommendBookByCategoryUseCase.execute(idUser);
    
    assertNull(recommendation);
    verify(repository, never()).recommendByCategory(anyList(), anyString());
  }
}
