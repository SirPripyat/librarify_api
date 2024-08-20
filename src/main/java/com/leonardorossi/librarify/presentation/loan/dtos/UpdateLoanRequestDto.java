package com.leonardorossi.librarify.presentation.loan.dtos;

import com.leonardorossi.librarify.domain.loan.enums.LoanStatusEnum;
import com.leonardorossi.librarify.presentation.loan.messages.LoanValidationMessages;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * DTO para edição de um novo usuário.
 * Contém as validações dos campos necessários.
 *
 * @param returnDate Data de devolução do livro.
 * @param status Status do empréstimo.
 */
public record UpdateLoanRequestDto(
    @NotNull(message = LoanValidationMessages.ID_USER_NOT_NULL)
    Long idUser,
  
    @NotNull(message = LoanValidationMessages.ID_BOOK_NOT_NULL)
    Long idBook,
    
    LocalDate returnDate,
    LoanStatusEnum status
) {
}
