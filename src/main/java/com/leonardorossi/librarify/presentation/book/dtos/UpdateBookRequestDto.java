package com.leonardorossi.librarify.presentation.book.dtos;

import com.leonardorossi.librarify.presentation.book.messages.BookValidationMessages;
import com.leonardorossi.librarify.shared.regexpatterns.RegexPatterns;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO para edição de um livro.
 * Contém as validações dos campos necessários.
 *
 * @param title Título do livro.
 * @param author Autor do livro.
 * @param category Categoria do livro.
 */
public record UpdateBookRequestDto(
    @Size(max = 255, message = BookValidationMessages.TITLE_SIZE)
    String title,
    
    @Size(max = 255, message = BookValidationMessages.AUTHOR_SIZE)
    @Pattern(regexp = RegexPatterns.ONLY_LETTERS,
      message = BookValidationMessages.AUTHOR_ONLY_LETTERS)
    String author,
    
    @Size(max = 50, message = BookValidationMessages.CATEGORY_SIZE)
    @Pattern(regexp = RegexPatterns.ONLY_LETTERS,
      message = BookValidationMessages.CATEGORY_ONLY_LETTERS)
    String category
) {
}
