package com.leonardorossi.librarify.application.user.gateways;

import com.leonardorossi.librarify.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface que define o contrato do repositório de usuários.
 * Implementações desta interface devem lidar com operações relacionadas a usuários.
 */
public interface UserRepository {
  User save(User user);
  
  boolean existsByEmail(String email);
  
  Optional<User> findById(Long id);
  
  Page<User> findAll(Pageable pageable);
  
}
