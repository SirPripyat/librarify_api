package com.leonardorossi.librarify.presentation.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

/**
 * DTO para criação de um novo usuário.
 * Contém as validações dos campos necessários.
 *
 * @param name Nome do usuário.
 * @param email E-mail do usuário.
 * @param registrationDate Data de cadastro do usuário.
 * @param phone Telefone do usuário.
 */
public record CreateUserRequestDto(
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    String name,
    
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    String email,
    
    @NotNull(message = "Data de cadastro é obrigatória")
    @PastOrPresent(message = "A data de cadastro deve ser no passado ou presente")
    LocalDate registrationDate,
    
    @NotBlank(message = "Telefone é obrigatório")
    @Size(max = 15, message = "O telefone deve ter no máximo 15 caracteres")
    String phone
) {}
