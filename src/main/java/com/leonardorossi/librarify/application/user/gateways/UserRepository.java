package com.leonardorossi.librarify.application.user.gateways;

import com.leonardorossi.librarify.domain.user.entity.User;

/**
 * Interface que define o contrato do repositório de usuários.
 * Implementações desta interface devem lidar com operações relacionadas a usuários.
 */
public interface UserRepository {
  User create(User user);
  
  boolean existsByEmail(String email);
}
