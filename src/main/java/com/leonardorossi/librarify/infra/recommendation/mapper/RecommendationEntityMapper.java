package com.leonardorossi.librarify.infra.recommendation.mapper;

import com.leonardorossi.librarify.domain.book.entity.Book;
import com.leonardorossi.librarify.domain.recommendation.Recommendation;
import com.leonardorossi.librarify.infra.book.mapper.BookEntityMapper;
import com.leonardorossi.librarify.infra.book.persistence.BookEntity;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Mapper responsável por converter entre a entidade de domínio {@link Recommendation} e a
 * entidade de persistência {@link BookEntity}.
 */
@Component
public class RecommendationEntityMapper {
  
  private final BookEntityMapper bookEntityMapper;
  
  public RecommendationEntityMapper(BookEntityMapper bookEntityMapper) {
    this.bookEntityMapper = bookEntityMapper;
  }
  
  /**
   * Converte uma List de {@link BookEntity} para um {@link Recommendation}.
   *
   * @param bookList Lista de entidades de persistência {@link BookEntity}.
   * @return Entidade de domínio {@link Recommendation}.
   */
  public Recommendation toDomain(List<BookEntity> bookList) {
    List<Book> books = bookList.stream().map(bookEntityMapper::toDomain).toList();
    
    return Recommendation.builder()
      .bookList(books)
      .build();
  }
}
