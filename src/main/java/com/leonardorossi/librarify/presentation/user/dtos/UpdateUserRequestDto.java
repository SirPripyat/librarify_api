package com.leonardorossi.librarify.presentation.user.dtos;

import com.leonardorossi.librarify.presentation.user.messages.UserValidationMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO para a edição de um novo usuário.
 * Contém as validações dos campos necessários.
 *
 * @param name Nome do usuário.
 * @param email E-mail do usuário.
 * @param phone Telefone do usuário.
 */
public record UpdateUserRequestDto(
    @Size(max = 100, message = UserValidationMessages.NAME_SIZE)
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$", message = UserValidationMessages.NAME_ALPHA_ONLY)
    String name,
    
    @Email(message = UserValidationMessages.EMAIL_VALID)
    String email,
    
    @Size(max = 15, message = UserValidationMessages.PHONE_SIZE)
    @Pattern(regexp = "^[0-9]+$", message = UserValidationMessages.PHONE_NUMERIC)
    String phone
) {
}
