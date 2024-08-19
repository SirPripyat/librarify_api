package com.leonardorossi.librarify.presentation.user.mapper;

import com.leonardorossi.librarify.domain.user.entity.User;
import com.leonardorossi.librarify.presentation.user.dtos.CreateUserRequestDto;
import org.springframework.stereotype.Component;

/**
 * Converte DTOs de requisição para entidades de usuário.
 */
@Component
public class UserRequestMapper {
  
  /**
    * Converte um {@link CreateUserRequestDto} para um {@link User}.
    *
    * @param requestDto Dados de entrada para criar um usuário.
    * @return A entidade {@link User} criada.
   */
  public User toEntity(CreateUserRequestDto requestDto) {
    return new User(
      null,
      requestDto.name(),
      requestDto.email(),
      requestDto.registrationDate(),
      requestDto.phone(),
      true
    );
  }
}
