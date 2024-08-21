package com.leonardorossi.librarify.presentation.user.messages;

/**
 * Classe que contém as mensagens de validação específicas para o contexto
 * de usuário.
 */
public class UserValidationMessages {
  public static final String NAME_NOT_BLANK = "Nome é obrigatório";
  public static final String NAME_SIZE = "O nome deve ter no máximo 100 caracteres";
  public static final String NAME_ALPHA_ONLY = "O nome deve conter apenas letras";
  
  public static final String EMAIL_NOT_BLANK = "Email é obrigatório";
  public static final String EMAIL_VALID = "Email deve ser válido";
  
  public static final String REGISTRATION_DATE_NOT_NULL = "Data de cadastro é obrigatória";
  public static final String REGISTRATION_DATE_PAST_OR_PRESENT =
      "A data de cadastro deve ser no passado ou presente";
  
  public static final String PHONE_NOT_BLANK = "Telefone é obrigatório";
  public static final String PHONE_SIZE = "O telefone deve ter entre 10 e 11 caracteres";
  public static final String PHONE_NUMERIC = "O telefone deve conter apenas números";
  
  private UserValidationMessages() {}
}
