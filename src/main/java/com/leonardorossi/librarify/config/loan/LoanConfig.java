package com.leonardorossi.librarify.config.loan;

import com.leonardorossi.librarify.application.book.usecase.FindOneBookUseCase;
import com.leonardorossi.librarify.application.loan.gateways.LoanRepository;
import com.leonardorossi.librarify.application.loan.usecase.CheckOutOneBookUseCase;
import com.leonardorossi.librarify.application.loan.usecase.FindOneLoanUseCase;
import com.leonardorossi.librarify.application.loan.usecase.UpdateOneLoanUseCase;
import com.leonardorossi.librarify.application.user.usecase.FindOneUserUseCase;
import com.leonardorossi.librarify.infra.loan.gateways.LoanRepositoryAdapter;
import com.leonardorossi.librarify.infra.loan.mapper.LoanEntityMapper;
import com.leonardorossi.librarify.infra.loan.persistence.LoanJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração para beans relacionados a empréstimos.
 */
@Configuration
public class LoanConfig {
  
  @Bean
  CheckOutOneBookUseCase checkOutOneBookUseCase(
      LoanRepository loanRepository,
      FindOneBookUseCase findOneBookUseCase,
      FindOneUserUseCase findOneUserUseCase
  ) {
    return new CheckOutOneBookUseCase(
      loanRepository,
      findOneBookUseCase,
      findOneUserUseCase
    );
  }
  
  @Bean
  FindOneLoanUseCase findOneLoanUseCase(
      LoanRepository loanRepository
  ) {
    return new FindOneLoanUseCase(
      loanRepository
    );
  }
  
  @Bean
  UpdateOneLoanUseCase updateOneLoanUseCase(
      LoanRepository loanRepository,
      FindOneLoanUseCase findOneLoanUseCase
  ) {
    return new UpdateOneLoanUseCase(
      loanRepository,
      findOneLoanUseCase
    );
  }
  
  @Bean
  LoanRepositoryAdapter loanRepositoryAdapter(
      LoanJpaRepository loanJpaRepository,
      LoanEntityMapper loanEntityMapper
  ) {
    return new LoanRepositoryAdapter(
      loanJpaRepository,
      loanEntityMapper
    );
  }
  
}
