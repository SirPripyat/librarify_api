package com.leonardorossi.librarify.presentation.book.messages;

/**
 * Classe que contém as mensagens de validação específicas para o contexto
 * de livro.
 */
public class BookValidationMessages {
  public static final String TITLE_NOT_BLANK = "O título não pode estar em branco.";
  public static final String TITLE_SIZE =  "O título não pode ter mais de 255 caracteres.";
  
  public static final String AUTHOR_NOT_BLANK = "O autor não pode estar em branco.";
  public static final String AUTHOR_SIZE = "O autor não pode ter mais de 255 caracteres.";
  public static final String AUTHOR_ONLY_LETTERS = "O autor só pode conter letras.";
  
  public static final String ISBN_NOT_BLANK = "O ISBN não pode estar em branco.";
  public static final String ISBN_SIZE = "O ISBN deve ter entre 10 e 13 caracteres.";
  public static final String ISBN_ONLY_NUMBERS = "O ISBN só pode conter números.";
  
  public static final String PUBLICATION_DATE_NOT_NULL =
      "A data de publicação não pode estar em branco.";
    
  public static final String CATEGORY_NOT_BLANK = "A categoria não pode estar em branco.";
  public static final String CATEGORY_SIZE = "A categoria não pode ter mais de 50 caracteres.";
  public static final String CATEGORY_ONLY_LETTERS = "A categoria só pode conter letras.";
  
  private BookValidationMessages() {}
}
