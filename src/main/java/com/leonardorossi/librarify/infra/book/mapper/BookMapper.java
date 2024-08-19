package com.leonardorossi.librarify.infra.book.mapper;

import com.leonardorossi.librarify.domain.book.entity.Book;
import com.leonardorossi.librarify.infra.book.persistence.BookEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper responsável por converter entre a entidade de domínio {@link  Book} e a entidade de
 * persistência {@link BookEntity}.
 */
@Component
public class BookMapper {
  
  /**
   * Converte um {@link Book} para um {@link BookEntity}.
   *
   * @param book Entidade de domínio {@link Book}.
   * @return Entidade de persistência {@link BookEntity}.
   */
  public BookEntity toEntity(Book book) {
    return BookEntity.builder()
      .id(book.getId())
      .title(book.getTitle())
      .author(book.getAuthor())
      .publicationDate(book.getPublicationDate())
      .isbn(book.getIsbn())
      .category(book.getCategory())
      .status(book.getStatus())
      .build();
  }
  
  /**
   * Converte um {@link BookEntity} para um {@link Book}.
   *
   * @param bookEntity Entidade de persistência {@link BookEntity}.
   * @return Entidade de domínio {@link Book}.
   */
  public Book toDomain(BookEntity bookEntity) {
    return Book.builder()
      .id(bookEntity.getId())
      .title(bookEntity.getTitle())
      .author(bookEntity.getAuthor())
      .publicationDate(bookEntity.getPublicationDate())
      .isbn(bookEntity.getIsbn())
      .category(bookEntity.getCategory())
      .status(bookEntity.getStatus())
      .build();
  }
}
