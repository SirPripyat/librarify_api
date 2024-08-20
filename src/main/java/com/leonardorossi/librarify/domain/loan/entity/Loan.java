package com.leonardorossi.librarify.domain.loan.entity;

import com.leonardorossi.librarify.domain.book.entity.Book;
import com.leonardorossi.librarify.domain.loan.enums.LoanStatusEnum;
import com.leonardorossi.librarify.domain.user.entity.User;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade de domínio que representa um empréstimo.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
  private Long id;
  private User user;
  private Book book;
  private LocalDate loanDate;
  private LocalDate returnDate;
  private LoanStatusEnum loanStatus;
}
