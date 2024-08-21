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
  
  /**
   * Construtor para a classe RecommendBookByCategoryUseCase.
   *
   * @param repository o repositório responsável por buscar recomendações
   * @param findLastBooksCheckedOutByUserUseCase o caso de uso responsável por buscar os últimos
   *                                             livros emprestados
   */
  public RecommendBookByCategoryUseCase(
      RecommendationRepository repository,
      FindLastBooksCheckedOutByUserUseCase findLastBooksCheckedOutByUserUseCase
  ) {
    this.repository = repository;
    this.findLastBooksCheckedOutByUserUseCase = findLastBooksCheckedOutByUserUseCase;
  }
  
  /**
   * Executa o caso de uso para recomendar um livro com base na categoria.
   *
   * @param idUser o ID do usuário para o qual a recomendação será feita
   * @return a recomendação gerada
   */
  public Recommendation execute(Long idUser) {
    List<Book> borrowedBooks = findLastBooksCheckedOutByUserUseCase.find(idUser);
    String mainCategory = findMainCategory(borrowedBooks);
    return repository.recommendByCategory(getBorrowedBooksIds(borrowedBooks), mainCategory);
  }
  
  /**
   * Encontra a categoria principal com base nos livros emprestados.
   *
   * @param borrowedBooks a lista de livros emprestados
   * @return a categoria mais frequente entre os livros emprestados
   */
  private String findMainCategory(List<Book> borrowedBooks) {
    return borrowedBooks.stream()
        .collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()))
        .entrySet().stream()
        .max(Map.Entry.comparingByValue())
        .map(Map.Entry::getKey)
        .orElse(null);
  }
  
  /**
   * Extrai os IDs dos livros emprestados.
   *
   * @param books a lista de livros
   * @return uma lista com os IDs dos livros
   */
  private List<Long> getBorrowedBooksIds(List<Book> books) {
    return books.stream()
        .map(Book::getId)
        .collect(Collectors.toList());
  }
}
