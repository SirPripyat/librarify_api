package com.leonardorossi.librarify.config.user;

import com.leonardorossi.librarify.application.user.gateways.UserRepository;
import com.leonardorossi.librarify.application.user.usecase.CreateUserUserCase;
import com.leonardorossi.librarify.infra.user.gateways.UserRepositoryAdapter;
import com.leonardorossi.librarify.infra.user.mapper.UserEntityMapper;
import com.leonardorossi.librarify.infra.user.persistence.UserJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração para beans relacionados a usuários.
 */
@Configuration
public class UserConfig {
  @Bean
  CreateUserUserCase createUserUserCase(UserRepository repository) {
    return new CreateUserUserCase(repository);
  }
  
  @Bean
  UserRepositoryAdapter userRepositoryAdapter(
      UserJpaRepository repository, UserEntityMapper mapper
  ) {
    return new UserRepositoryAdapter(repository, mapper);
  }
  
}
