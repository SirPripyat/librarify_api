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
  
  /**
   * Construtor para a classe FindLastBooksCheckedOutByUserUseCase.
   *
   * @param loanRepository o repositório responsável por buscar os empréstimos do usuário
   */
  public FindLastBooksCheckedOutByUserUseCase(LoanRepository loanRepository) {
    this.loanRepository = loanRepository;
  }
  
  /**
   * Busca os últimos livros emprestados por um usuário.
   *
   * @param userId o ID do usuário
   * @return uma lista de livros emprestados
   */
  public List<Book> find(Long userId) {
    return loanRepository.findLastBooksCheckOutByUser(userId).orElseThrow(() ->
      createNoContentRequestException(userId));
  }
  
  /**
   * Cria uma exceção personalizada para o caso de o usuário não ter livros emprestados.
   *
   * @param userId o ID do usuário
   * @return uma exceção personalizada
   */
  private CustomBadRequestException createNoContentRequestException(Long userId) {
    String errorMessage = String.format(LoanExceptionMessages.USER_DONT_HAVE_LOANS, userId);
    return new CustomBadRequestException(errorMessage);
  }
}
