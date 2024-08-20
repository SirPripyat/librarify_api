package com.leonardorossi.librarify.presentation.book.dtos;

import com.leonardorossi.librarify.presentation.book.messages.BookValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Builder;

/**
 * DTO para criação de um novo usuário.
 * Contém as validações dos campos necessários.
 *
 * @param title Título do livro.
 * @param author Autor do livro.
 * @param isbn ISBN do livro.
 * @param publicationDate Data de publicação do livro.
 * @param category Categoria do livro.
 */
@Builder
public record CreateBookRequestDto(
    @NotBlank(message = BookValidationMessages.TITLE_NOT_BLANK)
    @Size(max = 255, message = BookValidationMessages.TITLE_SIZE)
    String title,
    
    @NotBlank(message = BookValidationMessages.AUTHOR_NOT_BLANK)
    @Size(max = 255, message = BookValidationMessages.AUTHOR_SIZE)
    String author,
    
    @NotBlank(message = BookValidationMessages.ISBN_NOT_BLANK)
    @Size(min = 10, max = 13, message = BookValidationMessages.ISBN_SIZE)
    String isbn,
    
    @NotNull(message = BookValidationMessages.PUBLICATION_DATE_NOT_NULL)
    LocalDate publicationDate,
    
    @NotBlank(message = BookValidationMessages.CATEGORY_NOT_BLANK)
    @Size(max = 50, message = BookValidationMessages.CATEGORY_SIZE)
    String category
) {
}
