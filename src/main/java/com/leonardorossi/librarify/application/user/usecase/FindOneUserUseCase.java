package com.leonardorossi.librarify.application.user.usecase;

import com.leonardorossi.librarify.application.user.gateways.UserRepository;
import com.leonardorossi.librarify.domain.user.entity.User;
import com.leonardorossi.librarify.infra.exception.CustomBadRequestException;
import com.leonardorossi.librarify.infra.exception.CustomNoContentRequestException;
import com.leonardorossi.librarify.presentation.user.messages.UserExceptionMessages;

/**
 * Caso de uso para obter os detalhes de um único usuário ativo.
 */
public class FindOneUserUseCase {
  private final UserRepository userRepository;
  
  /**
   * Construtor para inicializar o repositório de usuários.
   *
   * @param userRepository o repositório responsável pelas operações de persistência de usuários
   */
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
  public User execute(Long id) {
    return userRepository.findOneById(id)
      .orElseThrow(() -> bookNotFoundException(id));
  }
  
  /**
   * Cria uma exceção para quando um usuário não for encontrado.
   *
   * @param id o ID do usuário não encontrado
   * @return a exceção customizada
   */
  private CustomNoContentRequestException bookNotFoundException(Long id) {
    return new CustomNoContentRequestException(
      String.format(UserExceptionMessages.USER_NOT_FOUND, id)
    );
  }
}
