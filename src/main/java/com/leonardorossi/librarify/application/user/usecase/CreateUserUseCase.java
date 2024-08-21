package com.leonardorossi.librarify.application.user.usecase;

import com.leonardorossi.librarify.application.user.gateways.UserRepository;
import com.leonardorossi.librarify.domain.user.entity.User;
import com.leonardorossi.librarify.infra.exception.CustomBadRequestException;
import com.leonardorossi.librarify.presentation.user.messages.UserExceptionMessages;

/**
 * Caso de uso responsável por criar um novo usuário.
 * Verifica se um usuário com o e-mail fornecido já existe antes de salvar.
 */
public class CreateUserUseCase {
  
  private final UserRepository userRepository;
  
  /**
   * Construtor para inicializar o repositório de usuários.
   *
   * @param userRepository o repositório responsável pelas operações de persistência de usuários
   */
  public CreateUserUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  /**
   * Cria um novo usuário, se o e-mail ainda não existir.
   *
   * @param user a entidade do usuário a ser criada
   * @return a entidade do usuário salva
   * @throws CustomBadRequestException se um usuário com o e-mail fornecido já existir
   */
  public User execute(User user) {
    validateEmailUniqueness(user.getEmail());
    return userRepository.save(user);
  }
  
  /**
   * Valida se o e-mail fornecido já está em uso.
   *
   * @param email o e-mail a ser verificado
   * @throws CustomBadRequestException se um usuário com o e-mail fornecido já existir
   */
  private void validateEmailUniqueness(String email) {
    if (userRepository.existsByEmail(email)) {
      throw new CustomBadRequestException(
        String.format(UserExceptionMessages.EMAIL_ALREADY_EXISTS, email)
      );
    }
  }
}
