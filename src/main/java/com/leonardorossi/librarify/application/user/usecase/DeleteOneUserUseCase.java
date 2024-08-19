package com.leonardorossi.librarify.application.user.usecase;

import com.leonardorossi.librarify.application.user.gateways.UserRepository;
import com.leonardorossi.librarify.domain.user.entity.User;

/**
 * Caso de uso responsável por deletar um usuário, caso ele exista.
 */
public class DeleteOneUserUseCase {
  
  private final UserRepository userRepository;
  private final FindOneUserUseCase findOneUserUseCase;
  
  public DeleteOneUserUseCase(
      UserRepository userRepository,
      FindOneUserUseCase findOneUserUseCase
  ) {
    this.userRepository = userRepository;
    this.findOneUserUseCase = findOneUserUseCase;
  }
  
  /**
   * Deleta um usuário se ele existir.
   *
   * @param id o ID do usuário a ser deletado
   */
  public User toggleStatus(Long id) {
    User findedUser = findOneUserUseCase.findById(id);
    
    findedUser.setStatus(!findedUser.getStatus());
    
    return userRepository.save(findedUser);
  }

}
