package com.leonardorossi.librarify.application.user.usecase;

import com.leonardorossi.librarify.application.user.gateways.UserRepository;
import com.leonardorossi.librarify.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Caso de uso para obter os detalhes de todos os usuários de maneira paginada.
 */
public class FindAllUsersUseCase {
  private final UserRepository userRepository;
  
  public FindAllUsersUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  /**
   * Obtém os detalhes de todos os usuários de maneira paginada.
   *
   * @param pageable objeto de paginação que define o número da página, o tamanho da página
   *      e a ordenação
   * @return a lista de usuários
   */
  public Page<User> findAll(Pageable pageable) {
    return userRepository.findAll(pageable);
  }
}
