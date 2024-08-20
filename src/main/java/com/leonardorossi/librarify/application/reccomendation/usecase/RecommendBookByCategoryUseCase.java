package com.leonardorossi.librarify.application.reccomendation.usecase;

import com.leonardorossi.librarify.application.loan.usecase.FindLastBooksCheckedOutByUserUseCase;
import com.leonardorossi.librarify.application.reccomendation.gateways.RecommendationRepository;
import com.leonardorossi.librarify.domain.book.entity.Book;
import com.leonardorossi.librarify.domain.recommendation.Recommendation;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Caso de uso para recomendar um livro por categoria.
 */
public class RecommendBookByCategoryUseCase {
  private final RecommendationRepository repository;
  private final FindLastBooksCheckedOutByUserUseCase findLastBooksCheckedOutByUserUseCase;
  
  public RecommendBookByCategoryUseCase(
      RecommendationRepository repository,
      FindLastBooksCheckedOutByUserUseCase findLastBooksCheckedOutByUserUseCase
  ) {
    this.repository = repository;
    this.findLastBooksCheckedOutByUserUseCase = findLastBooksCheckedOutByUserUseCase;
  }
  
  /**
   * Executa o caso de uso.
   */
  public Recommendation execute(Long idUser) {
    List<Book> bookList = findLastBooksCheckedOutByUserUseCase.find(idUser);
    List<Long> borrowedBooks = bookList.stream().map(Book::getId).collect(Collectors.toList());
    
    String category = findMainCategory(bookList);
    
    return repository.recommendByCategory(borrowedBooks, category);
  }
  
  private String findMainCategory(List<Book> borrowedBooks) {
    Map<String, Long> categoryCount = borrowedBooks.stream()
        .collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));
    
    return categoryCount.entrySet().stream()
      .max(Map.Entry.comparingByValue())
      .map(Map.Entry::getKey)
      .orElse(null);
  }
}
