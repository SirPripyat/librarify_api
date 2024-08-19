package com.leonardorossi.librarify.presentation.user.dtos;

import com.leonardorossi.librarify.presentation.user.messages.UserValidationMessages;
import jakarta.validation.constraints.*;

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
    String name,
    
    @Email(message = UserValidationMessages.EMAIL_VALID)
    String email,
    
    @Size(max = 15, message = UserValidationMessages.PHONE_SIZE)
    String phone
) {
}
