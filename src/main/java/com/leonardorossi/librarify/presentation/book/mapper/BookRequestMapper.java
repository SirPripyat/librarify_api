package com.leonardorossi.librarify.presentation.book.mapper;

import com.leonardorossi.librarify.domain.book.entity.Book;
import com.leonardorossi.librarify.presentation.book.dtos.CreateBookRequestDto;
import com.leonardorossi.librarify.presentation.book.dtos.UpdateBookRequestDto;
import org.springframework.stereotype.Component;

/**
 * Converte DTOs de requisição para entidades de livro.
 */
@Component
public class BookRequestMapper {
  
  /**
   * Converte um {@link CreateBookRequestDto} para um {@link Book}.
   *
   * @param requestDto Dados de entrada para criar um usuário.
   * @return A entidade {@link Book} criada.
   */
  public Book toEntity(CreateBookRequestDto requestDto) {
    return Book.builder()
      .title(requestDto.title())
      .author(requestDto.author())
      .isbn(requestDto.isbn())
      .publicationDate(requestDto.publicationDate())
      .category(requestDto.category())
      .status(true)
      .build();
  }
  
  /**
   * Converte um {@link UpdateBookRequestDto} para um {@link Book}.
   *
   * @param requestDto Dados de entrada para criar um usuário.
   * @return A entidade {@link Book} criada.
   */
  public Book toEntity(UpdateBookRequestDto requestDto) {
    return Book.builder()
      .title(requestDto.title())
      .author(requestDto.author())
      .category(requestDto.category())
      .build();
  }
  
  /**
   * Converte um Id de Livro para um {@link Book}.
   *
   * @param idBook Id de um livro.
   * @return A entidade {@link Book} atualizada.
   */
  public Book toEntity(Long idBook) {
    return Book.builder()
      .id(idBook)
      .build();
  }
}
