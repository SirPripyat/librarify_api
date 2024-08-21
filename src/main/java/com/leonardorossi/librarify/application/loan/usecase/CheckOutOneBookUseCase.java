package com.leonardorossi.librarify.application.loan.usecase;

import com.leonardorossi.librarify.application.book.usecase.FindOneBookUseCase;
import com.leonardorossi.librarify.application.loan.gateways.LoanRepository;
import com.leonardorossi.librarify.application.user.usecase.FindOneUserUseCase;
import com.leonardorossi.librarify.domain.book.entity.Book;
import com.leonardorossi.librarify.domain.loan.entity.Loan;
import com.leonardorossi.librarify.domain.user.entity.User;

/**
 * Caso de uso responsável por criar um empréstimo, associar um livro a um usuário.
 */
public class CheckOutOneBookUseCase {
  private final LoanRepository loanRepository;
  
  private final FindOneBookUseCase findOneBookUseCase;
  private final FindOneUserUseCase findOneUserUseCase;
  
  /**
   * Construtor da classe.
   */
  public CheckOutOneBookUseCase(
      LoanRepository loanRepository,
      FindOneBookUseCase findOneBookUseCase,
      FindOneUserUseCase findOneUserUseCase
  ) {
    this.loanRepository = loanRepository;
    this.findOneBookUseCase = findOneBookUseCase;
    this.findOneUserUseCase = findOneUserUseCase;
  }
  
  /**
   * Executa o caso de uso.
   *
   * @param loan entidade de empréstimo.
   * @return Empréstimo criado.
   */
  public Loan execute(Loan loan) {
    User user = findOneUserUseCase.findById(loan.getUser().getId());
    Book book = findOneBookUseCase.execute(loan.getBook().getId());
    
    Loan buildedLoan = buildLoan(loan, user, book);
    
    return loanRepository.save(buildedLoan);
  }
  
  private Loan buildLoan(Loan loan, User user, Book book) {
    return Loan.builder()
      .user(user)
      .book(book)
      .loanDate(loan.getLoanDate())
      .returnDate(loan.getReturnDate())
      .loanStatus(loan.getLoanStatus())
      .build();
  }
}
