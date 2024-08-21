package com.leonardorossi.librarify.presentation.user.messages;

/**
 * Classe que contém as mensagens de exceção específicas para o contexto de
 * usuário.
 */
public class UserExceptionMessages {
  public static final String EMAIL_ALREADY_EXISTS =
      "User with email: %s already exists.";
  
  public static final String USER_NOT_FOUND = "User with id: %s not found.";
  
  public static final String PAGINATED_USERS_NOT_FOUND = "No users found.";
  
  private UserExceptionMessages() {
  }
}
