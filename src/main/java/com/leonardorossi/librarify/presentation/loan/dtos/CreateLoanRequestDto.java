package com.leonardorossi.librarify.presentation.loan.dtos;

import com.leonardorossi.librarify.domain.loan.entity.enums.LoanStatusEnum;
import com.leonardorossi.librarify.presentation.loan.messages.LoanValidationMessages;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;

/**
 * DTO para criação de um novo usuário.
 * Contém as validações dos campos necessários.
 *
 * @param idUser Id do usuário.
 * @param idBook Id do livro.
 * @param loanDate Data de empréstimo do livro.
 * @param returnDate Data de devolução do livro.
 * @param status Status do empréstimo.
 */
public record CreateLoanRequestDto(
    @NotNull(message = LoanValidationMessages.ID_USER_NOT_NULL)
    Long idUser,
    
    @NotNull(message = LoanValidationMessages.ID_BOOK_NOT_NULL)
    Long idBook,
    
    @NotNull(message = LoanValidationMessages.LOAN_DATE_NOT_NULL)
    @PastOrPresent(message = LoanValidationMessages.LOAN_DATE_PAST)
    LocalDate loanDate,
    
    @NotNull(message = LoanValidationMessages.RETURN_DATE_NOT_NULL)
    LocalDate returnDate,
    
    @NotNull(message = LoanValidationMessages.STATUS_NOT_NULL)
    LoanStatusEnum status
) {
}
