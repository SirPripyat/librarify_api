package com.leonardorossi.librarify.application.book.usecase;

import com.leonardorossi.librarify.infra.exception.CustomBadRequestException;

/**
 * Caso de uso responsável por validar um ISBN-10 e ISBN-13.
 */
public class ValidateIsbnUseCase {
  
  private static final int ISBN_10_LENGTH = 10;
  private static final int ISBN_13_LENGTH = 13;
  
  public ValidateIsbnUseCase() {}
  
  /**
   * Valida um ISBN-10 ou ISBN-13.
   *
   * @param isbn o ISBN a ser validado
   * @return true se o ISBN for válido, false caso contrário
   * @throws CustomBadRequestException se o ISBN inválido
   */
  public boolean execute(String isbn) {
    if (isbn == null || (!isValidLength(isbn))) {
      return false;
    }
    
    return (isbn.length() == ISBN_10_LENGTH) ? isValidIsbn10(isbn) : isValidIsbn13(isbn);
  }
  
  private boolean isValidLength(String isbn) {
    return isbn.length() == ISBN_10_LENGTH || isbn.length() == ISBN_13_LENGTH;
  }
  
  private boolean isValidIsbn10(String isbn) {
    int sum = 0;
    for (int i = 0; i < 9; i++) {
      if (!Character.isDigit(isbn.charAt(i))) {
        return false;
      }
      sum += (ISBN_10_LENGTH - i) * Character.getNumericValue(isbn.charAt(i));
    }
    
    char lastChar = isbn.charAt(9);
    sum += (lastChar == 'X') ? 10 : Character.getNumericValue(lastChar);
    
    return sum % 11 == 0;
  }
  
  private boolean isValidIsbn13(String isbn) {
    int sum = 0;
    for (int i = 0; i < 12; i++) {
      if (!Character.isDigit(isbn.charAt(i))) {
        return false;
      }
      int digit = Character.getNumericValue(isbn.charAt(i));
      sum += (i % 2 == 0) ? digit : digit * 3;
    }
    
    int checksum = (10 - (sum % 10)) % 10;
    return checksum == Character.getNumericValue(isbn.charAt(12));
  }
}
