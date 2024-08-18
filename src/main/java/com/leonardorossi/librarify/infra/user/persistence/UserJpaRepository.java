package com.leonardorossi.librarify.infra.user.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório JPA para realizar operações de persistência na entidade {@link UserEntity}.
 * Fornece métodos padrão do Spring Data JPA e consultas personalizadas.
 */
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
  boolean existsByEmail(String email);
}
