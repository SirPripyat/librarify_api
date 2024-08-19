package com.leonardorossi.librarify.application.user.usecase;

import com.leonardorossi.librarify.application.user.gateways.UserRepository;
import com.leonardorossi.librarify.domain.user.entity.User;
import com.leonardorossi.librarify.infra.exception.CustomBadRequestException;
import com.leonardorossi.librarify.presentation.user.messages.UserExceptionMessages;

/**
 * Caso de uso responsável por deletar um usuário, caso ele exista.
 */
public class DeleteOneUserUseCase {
  
  private final UserRepository userRepository;
  
  public DeleteOneUserUseCase(
      UserRepository userRepository
  ) {
    this.userRepository = userRepository;
  }
  
  /**
   * Deleta um usuário se ele existir.
   *
   * @param id o ID do usuário a ser deletado
   */
  public User toggleStatus(Long id) {
    User user = findById(id);
    user.setStatus(!user.getStatus());
    return userRepository.save(user);
  }

  private User findById(Long id) {
    return userRepository.findOneById(id).orElseThrow(() ->
      new CustomBadRequestException(
        String.format(UserExceptionMessages.USER_NOT_FOUND, id)
      ));
  }
}
