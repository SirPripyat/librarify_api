package com.leonardorossi.librarify.application.loan.usecase;

import com.leonardorossi.librarify.application.loan.gateways.LoanRepository;
import com.leonardorossi.librarify.domain.book.entity.Book;
import com.leonardorossi.librarify.infra.exception.CustomBadRequestException;
import com.leonardorossi.librarify.presentation.loan.messages.LoanExceptionMessages;
import java.util.List;

/**
 * Caso de uso que busca os últimos livros emprestados por um usuário.
 */
public class FindLastBooksCheckedOutByUserUseCase {
  
  private final LoanRepository loanRepository;
  
  public FindLastBooksCheckedOutByUserUseCase(LoanRepository loanRepository) {
    this.loanRepository = loanRepository;
  }
  
  /**
    * Busca os últimos livros emprestados por um usuário.
   */
  public List<Book> find(Long userId, int limit) {
    return loanRepository.findLastBooksCheckOutByUser(userId, limit).orElseThrow(() ->
      new CustomBadRequestException(
        String.format(LoanExceptionMessages.USER_DONT_HAVE_LOANS, userId)
      ));
  }
}
