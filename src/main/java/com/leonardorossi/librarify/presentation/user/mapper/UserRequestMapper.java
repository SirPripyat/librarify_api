package com.leonardorossi.librarify.presentation.user.mapper;

import com.leonardorossi.librarify.domain.user.entity.User;
import com.leonardorossi.librarify.presentation.user.dtos.CreateUserRequestDto;
import com.leonardorossi.librarify.presentation.user.dtos.UpdateUserRequestDto;
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
  
  /**
   * Converte um {@link UpdateUserRequestDto} para um {@link User}.
   *
   * @param requestDto Dados de entrada para atualizar um usuário.
   * @return A entidade {@link User} atualizada.
   */
  public User toEntity(UpdateUserRequestDto requestDto) {
    return User.builder()
      .name(requestDto.name())
      .email(requestDto.email())
      .phone(requestDto.phone())
      .build();
  }
  
  /**
   * Converte um Id de Usuário para um {@link User}.
   *
   * @param idUser Id de um usuário.
   * @return A entidade {@link User} atualizada.
   */
  public User toEntity(Long idUser) {
    return User.builder()
      .id(idUser)
      .build();
  }
}
