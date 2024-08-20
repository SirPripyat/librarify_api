package com.leonardorossi.librarify.infra.loan.mapper;

import com.leonardorossi.librarify.domain.loan.entity.Loan;
import com.leonardorossi.librarify.infra.book.mapper.BookEntityMapper;
import com.leonardorossi.librarify.infra.loan.persistence.LoanEntity;
import com.leonardorossi.librarify.infra.user.mapper.UserEntityMapper;
import org.springframework.context.annotation.Configuration;

/**
 * Mapper responsável por converter entre a entidade de domínio {@link Loan} e a entidade de
 * persistência {@link LoanEntity}.
 */
@Configuration
public class LoanEntityMapper {
  
  private final UserEntityMapper userEntityMapper;
  private final BookEntityMapper bookEntityMapper;
  
  public LoanEntityMapper(UserEntityMapper userEntityMapper, BookEntityMapper bookEntityMapper) {
    this.userEntityMapper = userEntityMapper;
    this.bookEntityMapper = bookEntityMapper;
  }
  
  /**
   * Converte um {@link Loan} para um {@link LoanEntity}.
   *
   * @param loan Entidade de domínio {@link Loan}.
   * @return Entidade de persistência {@link LoanEntity}.
   */
  public LoanEntity toEntity(Loan loan) {
    return LoanEntity.builder()
      .id(loan.getId())
      .userEntity(userEntityMapper.toEntity(loan.getUser()))
      .bookEntity(bookEntityMapper.toEntity(loan.getBook()))
      .loanDate(loan.getLoanDate())
      .returnDate(loan.getReturnDate())
      .loanStatus(loan.getLoanStatus())
      .build();
  }
  
  /**
   * Converte um {@link LoanEntity} para um {@link Loan}.
   *
   * @param loanEntity Entidade de persistência {@link LoanEntity}.
   * @return Entidade de domínio {@link Loan}.
   */
  public Loan toDomain(LoanEntity loanEntity) {
    return Loan.builder()
      .id(loanEntity.getId())
      .user(userEntityMapper.toDomain(loanEntity.getUserEntity()))
      .book(bookEntityMapper.toDomain(loanEntity.getBookEntity()))
      .loanDate(loanEntity.getLoanDate())
      .returnDate(loanEntity.getReturnDate())
      .loanStatus(loanEntity.getLoanStatus())
      .build();
  }
}
