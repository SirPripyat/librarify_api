package com.leonardorossi.librarify.infra.loan.persistence;

import com.leonardorossi.librarify.domain.loan.entity.enums.LoanStatusEnum;
import com.leonardorossi.librarify.infra.book.persistence.BookEntity;
import com.leonardorossi.librarify.infra.user.persistence.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade que representa o usuário na base de dados.
 * Mapeada para a tabela "EMPRESTIMO".
 */
@Entity
@Table(name = "EMPRESTIMO")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_EMPRESTIMO")
  private Long id;
  
  @Column(name = "DATA_EMPRESTIMO", nullable = false)
  private LocalDate loanDate;
  
  @Column(name = "DATA_DEVOLUCAO", nullable = false)
  private LocalDate returnDate;
  
  @Enumerated(EnumType.STRING)
  @Column(name = "STATUS", nullable = false)
  private LoanStatusEnum loanStatus;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_USUARIO", nullable = false)
  private UserEntity userEntity;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_LIVRO", nullable = false)
  private BookEntity bookEntity;
}
