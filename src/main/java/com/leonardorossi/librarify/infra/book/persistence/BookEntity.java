package com.leonardorossi.librarify.infra.book.persistence;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

/**
 * Entidade que representa o livro na base de dados.
 * Mapeada para a tabela "LIVRO".
 */
@Entity
@Table(name = "LIVRO")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_LIVRO")
  private Long id;
  
  @Column(name = "TITULO", nullable = false)
  private String title;
  
  @Column(name = "AUTOR", nullable = false)
  private String author;
  
  @Column(name = "ISBN", nullable = false,  unique = true)
  private String isbn;
  
  @Column(name = "DATA_PUBLICACAO", nullable = false)
  private LocalDate publicationDate;
  
  @Column(name = "CATEGORIA", nullable = false)
  private String category;
  
  @Column(name = "STATUS", nullable = false)
  private Boolean status;
}
