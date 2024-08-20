package com.leonardorossi.librarify.presentation.loan.messages;

/**
 * Classe que contém as mensagens de exceção específicas para o contexto de
 * empréstimo.
 */
public class LoanExceptionMessages {
  public static final String LOAN_NOT_FOUND = "Loan with id: %s not found.";
  
  public static final String USER_DONT_HAVE_LOANS = "User with id: %s don't have loans.";
  
  private LoanExceptionMessages() {
  }
}
