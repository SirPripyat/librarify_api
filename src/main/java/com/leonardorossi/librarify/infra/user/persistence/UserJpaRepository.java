package com.leonardorossi.librarify.infra.user.persistence;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

/**
 * Repositório JPA para realizar operações de persistência na entidade {@link UserEntity}.
 * Fornece métodos padrão do Spring Data JPA e consultas personalizadas.
 */
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
  boolean existsByEmail(String email);
  
  Optional<UserEntity> findOneByIdAndStatusIsTrue(Long id);
  
  @NonNull
  Page<UserEntity> findAll(@NonNull Pageable pageable);
  
  Optional<UserEntity> findOneById(Long id);
}
