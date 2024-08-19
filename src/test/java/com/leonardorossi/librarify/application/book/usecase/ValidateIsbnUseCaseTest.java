package com.leonardorossi.librarify.application.book.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidateIsbnUseCaseTest {
  
  private ValidateIsbnUseCase validateIsbnUseCase;
  
  @BeforeEach
  void setUp() {
    validateIsbnUseCase = new ValidateIsbnUseCase();
  }

  @Test
  void shouldReturnTrueWhenIsbn10IsValid() {
    String isbn10 = "0306406152";
    
    boolean result = validateIsbnUseCase.execute(isbn10);
    
    assertTrue(result);
  }
  
  @Test
  void shouldReturnFalseWhenIsbn10IsInvalid() {
    String isbn10 = "0306406151";
    
    boolean result = validateIsbnUseCase.execute(isbn10);
    
    assertFalse(result);
  }
  
  @Test
  void shouldReturnTrueWhenIsbn13IsValid() {
    String isbn13 = "9783161484100";
    
    boolean result = validateIsbnUseCase.execute(isbn13);
    
    assertTrue(result);
  }
  
  @Test
  void shouldReturnFalseWhenIsbn13IsInvalid() {
    String isbn13 = "9783161484101";
    
    boolean result = validateIsbnUseCase.execute(isbn13);
    
    assertFalse(result);
  }
  
  @Test
  void shouldReturnFalseWhenIsbnLengthIsInvalid() {
    String isbn = "0000";
    
    boolean result = validateIsbnUseCase.execute(isbn);
    
    assertFalse(result);
  }
  
  @Test
  void shouldReturnFalseWhenIsbnHasLetters() {
    String isbn = "9783161484A0";
    
    boolean result = validateIsbnUseCase.execute(isbn);
    
    assertFalse(result);
  }
}
