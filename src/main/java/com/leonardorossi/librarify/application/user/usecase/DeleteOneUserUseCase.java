package com.leonardorossi.librarify.application.user.usecase;

import com.leonardorossi.librarify.application.user.gateways.UserRepository;
import com.leonardorossi.librarify.domain.user.entity.User;

/**
 * Caso de uso responsável por deletar um usuário, caso ele exista.
 */
public class DeleteOneUserUseCase {
  
  private final UserRepository userRepository;
  private final FindOneUserUseCase findOneUserUseCase;
  
  /**
   * Construtor para inicializar as dependências necessárias.
   *
   * @param userRepository      o repositório responsável pelas operações de persistência de
   *                            usuários
   * @param findOneUserUseCase  o caso de uso responsável por buscar um usuário pelo ID
   */
  public DeleteOneUserUseCase(
      UserRepository userRepository, FindOneUserUseCase findOneUserUseCase
  ) {
    this.userRepository = userRepository;
    this.findOneUserUseCase = findOneUserUseCase;
  }
  
  /**
   * Alterna o status de um usuário, se ele existir.
   *
   * @param id o ID do usuário cujo status será alternado
   * @return o usuário com o status atualizado
   */
  public User execute(Long id) {
    User user = findOneUserUseCase.execute(id);
    toggleStatus(user);
    return userRepository.save(user);
  }
  
  /**
   * Altera o status de um usuário.
   *
   * @param user a entidade de usuário que o status irá ser alterado
   */
  private void toggleStatus(User user) {
    user.setStatus(!user.getStatus());
  }
}
