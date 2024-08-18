package com.leonardorossi.librarify.application.user.usecase;

import com.leonardorossi.librarify.application.user.gateways.UserRepository;
import com.leonardorossi.librarify.domain.user.entity.User;
import com.leonardorossi.librarify.infra.exception.CustomBadRequestException;

/**
 * Caso de uso responsável por criar um novo usuário.
 * Verifica se um usuário com o e-mail fornecido já existe antes de salvar.
 */
public class CreateUserUserCase {
  
  private final UserRepository userRepository;
  
  public CreateUserUserCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  /**
   * Cria um novo usuário se o e-mail ainda não existir.
   *
   * @param user a entidade do usuário a ser criada
   * @return a entidade do usuário salva
   * @throws CustomBadRequestException se um usuário com o e-mail fornecido já existir
   */
  public User create(User user) {
    if (userRepository.existsByEmail(user.getEmail())) {
      throw new CustomBadRequestException(
        "User with email: " + user.getEmail() + " already exists."
      );
    }
    
    return userRepository.create(user);
  }
}
