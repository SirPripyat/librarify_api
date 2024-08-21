package com.leonardorossi.librarify.application.user.usecase;

import com.leonardorossi.librarify.application.user.gateways.UserRepository;
import com.leonardorossi.librarify.domain.user.entity.User;
import com.leonardorossi.librarify.infra.exception.CustomBadRequestException;
import com.leonardorossi.librarify.presentation.user.messages.UserExceptionMessages;

/**
 * Caso de uso para atualizar os detalhes de um único usuário.
 */
public class UpdateOneUserUseCase {
  private final UserRepository userRepository;
  private final FindOneUserUseCase findOneUserUseCase;
  
  /**
   * Construtor para a classe UpdateOneUserUseCase.
   *
   * @param userRepository      o repositório responsável por salvar o usuário atualizado
   * @param findOneUserUseCase  o caso de uso responsável por buscar o usuário pelo ID antes
   *                            de atualizá-lo
   */
  public UpdateOneUserUseCase(
      UserRepository userRepository, FindOneUserUseCase findOneUserUseCase
  ) {
    this.userRepository = userRepository;
    this.findOneUserUseCase = findOneUserUseCase;
  }
  
  /**
   * Atualiza os detalhes de um usuário existente.
   *
   * @param id   o ID do usuário a ser atualizado
   * @param user a entidade contendo os novos detalhes do usuário
   * @return a entidade do usuário atualizada
   */
  public User execute(Long id, User user) {
    User userToUpdate = findOneUserUseCase.execute(id);
    
    updateFields(userToUpdate, user);
    
    return userRepository.save(userToUpdate);
  }
  
  /**
   * Atualiza os campos do usuário existente com os novos valores fornecidos.
   *
   * @param userToUpdate o usuário existente a ser atualizado
   * @param updatedUser  a entidade contendo os novos detalhes do usuário
   */
  private void updateFields(User userToUpdate, User updatedUser) {
    if (updatedUser.getName() != null) {
      userToUpdate.setName(updatedUser.getName());
    }
    
    if (updatedUser.getEmail() != null) {
      validateEmail(updatedUser.getEmail());
      userToUpdate.setEmail(updatedUser.getEmail());
    }
    
    if (updatedUser.getPhone() != null) {
      userToUpdate.setPhone(updatedUser.getPhone());
    }
  }

  /**
   * Valida se o novo e-mail já está em uso por outro usuário.
   *
   * @param email        o novo e-mail a ser validado
   */
  private void validateEmail(String email) {
    if (userRepository.existsByEmail(email)) {
      throw new CustomBadRequestException(
        String.format(UserExceptionMessages.EMAIL_ALREADY_EXISTS, email)
      );
    }
  }
}
