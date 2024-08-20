package com.leonardorossi.librarify.infra.loan.gateways;

import com.leonardorossi.librarify.application.loan.gateways.LoanRepository;
import com.leonardorossi.librarify.domain.loan.entity.Loan;
import com.leonardorossi.librarify.infra.loan.mapper.LoanEntityMapper;
import com.leonardorossi.librarify.infra.loan.persistence.LoanEntity;
import com.leonardorossi.librarify.infra.loan.persistence.LoanJpaRepository;

/**
 * Adaptador que implementa o repositório de empréstimos para conectar a camada de domínio com a
 * infraestrutura.
 * Este adaptador converte entre entidades de domínio e entidades persistidas.
 */
public class LoanRepositoryAdapter implements LoanRepository {
  private final LoanJpaRepository repository;
  private final LoanEntityMapper mapper;
  
  public LoanRepositoryAdapter(
      LoanJpaRepository loanJpaRepository,
      LoanEntityMapper loanEntityMapper
  ) {
    this.repository = loanJpaRepository;
    this.mapper = loanEntityMapper;
  }
  
  @Override
  public Loan save(Loan loan) {
    LoanEntity loanEntity = mapper.toEntity(loan);
    
    return mapper.toDomain(repository.save(loanEntity));
  }
}
