package com.leonardorossi.librarify.presentation.user.dtos;

import com.leonardorossi.librarify.presentation.user.messages.UserValidationMessages;
import com.leonardorossi.librarify.shared.regexpatterns.RegexPatterns;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Builder;

/**
 * DTO para criação de um novo usuário.
 * Contém as validações dos campos necessários.
 *
 * @param name Nome do usuário.
 * @param email E-mail do usuário.
 * @param registrationDate Data de cadastro do usuário.
 * @param phone Telefone do usuário.
 */
@Builder
public record CreateUserRequestDto(
    @NotBlank(message = UserValidationMessages.NAME_NOT_BLANK)
    @Size(max = 100, message = UserValidationMessages.NAME_SIZE)
    @Pattern(regexp = RegexPatterns.ONLY_LETTERS, message = UserValidationMessages.NAME_ALPHA_ONLY)
    String name,
    
    @NotBlank(message = UserValidationMessages.EMAIL_NOT_BLANK)
    @Email(message = UserValidationMessages.EMAIL_VALID)
    String email,
    
    @NotNull(message = UserValidationMessages.REGISTRATION_DATE_NOT_NULL)
    @PastOrPresent(message = UserValidationMessages.REGISTRATION_DATE_PAST_OR_PRESENT)
    LocalDate registrationDate,
    
    @NotBlank(message = UserValidationMessages.PHONE_NOT_BLANK)
    @Size(min = 10, max = 11, message = UserValidationMessages.PHONE_SIZE)
    @Pattern(regexp = RegexPatterns.ONLY_NUMBERS, message = UserValidationMessages.PHONE_NUMERIC)
    String phone
) {}
