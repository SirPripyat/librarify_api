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
  
  public UpdateOneUserUseCase(
      UserRepository userRepository, FindOneUserUseCase findOneUserUseCase
  ) {
    this.userRepository = userRepository;
    this.findOneUserUseCase = findOneUserUseCase;
  }
  
  /**
   * Atualiza os detalhes de um único usuário.
   *
   * @param user a entidade do usuário a ser atualizada
   * @return a entidade do usuário atualizada
   */
  public User update(Long id, User user) {
    User userToUpdate = findOneUserUseCase.findById(id);
    
    updateFields(userToUpdate, user);
    
    return userRepository.save(userToUpdate);
  }
  
  private void updateFields(User userToUpdate, User user) {
    if (user.getName() != null) {
      userToUpdate.setName(user.getName());
    }
    
    if (user.getEmail() != null) {
      validateEmail(user.getEmail());
      userToUpdate.setEmail(user.getEmail());
    }
    
    if (user.getPhone() != null) {
      userToUpdate.setPhone(user.getPhone());
    }
  }
  
  private void validateEmail(String email) {
    if (userRepository.existsByEmail(email)) {
      throw new CustomBadRequestException(
        String.format(UserExceptionMessages.EMAIL_ALREADY_EXISTS, email)
      );
    }
  }
}
