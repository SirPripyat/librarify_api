package com.leonardorossi.librarify.infra.book.persistence;

import com.leonardorossi.librarify.infra.loan.persistence.LoanEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
  
  @OneToMany(mappedBy = "bookEntity", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<LoanEntity> listOfLoans;
}
