package com.leonardorossi.librarify.domain.book.entity;

import java.time.LocalDate;
import lombok.*;

/**
 * Entidade de domínio que representa um livro.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
  private Long id;
  private String title;
  private String author;
  private String isbn;
  private LocalDate publicationDate;
  private String category;
  private Boolean status;
}
