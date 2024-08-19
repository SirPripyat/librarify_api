package com.leonardorossi.librarify.application.user.usecase;

import com.leonardorossi.librarify.application.user.gateways.UserRepository;
import com.leonardorossi.librarify.domain.user.entity.User;
import com.leonardorossi.librarify.infra.exception.CustomBadRequestException;
import com.leonardorossi.librarify.presentation.user.messages.UserExceptionMessages;

/**
 * Caso de uso para obter os detalhes de um único usuário.
 */
public class FindOneUserUseCase {
  private final UserRepository userRepository;
  
  public FindOneUserUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  /**
   * Obtém os detalhes de um único usuário, caso ele exista.
   *
   * @param id a entidade do usuário a ser criada
   * @return a entidade do usuário
   * @throws CustomBadRequestException se o usuário não existir.
   */
  public User findById(Long id) {
    return userRepository.findById(id).orElseThrow(() ->
      new CustomBadRequestException(
        String.format(UserExceptionMessages.USER_NOT_FOUND, id)
      )
    );
  }
}
