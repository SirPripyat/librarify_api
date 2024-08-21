package com.leonardorossi.librarify.presentation.user.messages;

/**
 * Classe que contém as mensagens de exceção específicas para o contexto de
 * usuário.
 */
public class UserExceptionMessages {
  public static final String EMAIL_ALREADY_EXISTS =
      "Usuário com e-mail: %s já existe.";
  
  public static final String USER_NOT_FOUND = "Usuário com id: %s não encontrado.";
  
  public static final String PAGINATED_USERS_NOT_FOUND = "Nenhum usuário encontrado.";
  
  private UserExceptionMessages() {
  }
}
