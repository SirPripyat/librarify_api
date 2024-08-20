package com.leonardorossi.librarify.infra.loan.gateways;

import com.leonardorossi.librarify.application.loan.gateways.LoanRepository;
import com.leonardorossi.librarify.domain.book.entity.Book;
import com.leonardorossi.librarify.domain.loan.entity.Loan;
import com.leonardorossi.librarify.infra.book.mapper.BookEntityMapper;
import com.leonardorossi.librarify.infra.loan.mapper.LoanEntityMapper;
import com.leonardorossi.librarify.infra.loan.persistence.LoanEntity;
import com.leonardorossi.librarify.infra.loan.persistence.repository.LoanJpaRepository;
import java.util.List;
import java.util.Optional;

/**
 * Adaptador que implementa o repositório de empréstimos para conectar a camada de domínio com a
 * infraestrutura.
 * Este adaptador converte entre entidades de domínio e entidades persistidas.
 */
public class LoanRepositoryAdapter implements LoanRepository {
  private final LoanJpaRepository repository;
  
  private final LoanEntityMapper mapper;
  private final BookEntityMapper bookEntityMapper;
  
  /**
  * Construtor que recebe as dependências necessárias para o adaptador.
   */
  public LoanRepositoryAdapter(
      LoanJpaRepository loanJpaRepository,
      LoanEntityMapper loanEntityMapper,
      BookEntityMapper bookEntityMapper
  ) {
    this.repository = loanJpaRepository;
    this.mapper = loanEntityMapper;
    this.bookEntityMapper = bookEntityMapper;
  }
  
  @Override
  public Loan save(Loan loan) {
    LoanEntity loanEntity = mapper.toEntity(loan);
    
    return mapper.toDomain(repository.save(loanEntity));
  }
  
  @Override
  public Optional<Loan> findOneById(Long id) {
    Optional<LoanEntity> loanEntity = repository.findOneById(id);
    return loanEntity.map(mapper::toDomain);
  }
  
  @Override
  public Optional<List<Book>> findLastBooksCheckOutByUser(Long userId, int limit) {
    return repository.findLastBooksCheckOutByUser(userId, limit)
      .map(bookEntities -> bookEntities.stream()
        .map(bookEntityMapper::toDomain)
        .toList());
  }
}
