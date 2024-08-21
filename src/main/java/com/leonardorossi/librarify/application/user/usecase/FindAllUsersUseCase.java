package com.leonardorossi.librarify.application.user.usecase;

import com.leonardorossi.librarify.application.user.gateways.UserRepository;
import com.leonardorossi.librarify.domain.user.entity.User;
import com.leonardorossi.librarify.infra.exception.CustomBadRequestException;
import com.leonardorossi.librarify.presentation.user.messages.UserExceptionMessages;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Caso de uso para obter os detalhes de todos os usuários de maneira paginada.
 */
public class FindAllUsersUseCase {
  private final UserRepository userRepository;
  
  /**
   * Construtor para inicializar o repositório de usuários.
   *
   * @param userRepository o repositório responsável pelas operações de persistência de usuários
   */
  public FindAllUsersUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  /**
   * Obtém os detalhes de todos os usuários de maneira paginada.
   *
   * @param pageable objeto de paginação que define o número da página, o tamanho da página e a
   *                 ordenação
   * @return uma página contendo a lista de usuários
   */
  public Page<User> execute(Pageable pageable) {
    Page<User> userPage = userRepository.findAll(pageable);
    validateUserDoesNotExist(userPage);
    return userRepository.findAll(pageable);
  }
  
  /**
   * Valida se a página de usuários está vazia.
   *
   * @param users a página de usuários
   */
  private void validateUserDoesNotExist(Page<User> users) {
    if (users.isEmpty()) {
      throw new CustomBadRequestException(UserExceptionMessages.PAGINATED_USERS_NOT_FOUND);
    }
  }
}
