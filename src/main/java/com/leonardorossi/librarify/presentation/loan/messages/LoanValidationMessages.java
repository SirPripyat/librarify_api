package com.leonardorossi.librarify.presentation.loan.messages;

/**
 * Classe que contém as mensagens de validação específicas para o contexto
 * de empréstimo.
 */
public class LoanValidationMessages {
  public static final String ID_USER_NOT_NULL = "O id do usuário não pode estar em branco.";
  
  public static final String ID_BOOK_NOT_NULL = "O id do livro não pode estar em branco.";
  
  public static final String LOAN_DATE_NOT_NULL = "A data de empréstimo não pode estar em branco.";
  public static final String LOAN_DATE_PAST = "A data de empréstimo não pode ser futura.";
  
  public static final String RETURN_DATE_NOT_NULL = "A data de devolução não pode estar em branco.";
  
  public static final String STATUS_NOT_NULL = "O status do empréstimo não pode estar em branco.";
  
  private LoanValidationMessages() {}
}
