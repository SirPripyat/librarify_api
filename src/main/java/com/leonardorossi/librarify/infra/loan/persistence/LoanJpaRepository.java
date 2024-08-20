package com.leonardorossi.librarify.infra.loan.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório JPA para realizar operações de persistência na entidade {@link LoanEntity}.
 * Fornece métodos padrão do Spring Data JPA e consultas personalizadas.
 */
public interface LoanJpaRepository extends JpaRepository<LoanEntity, Long> {
  Optional<LoanEntity> findOneById(Long idLoan);
}
