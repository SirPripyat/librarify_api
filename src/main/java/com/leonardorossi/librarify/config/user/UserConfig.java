package com.leonardorossi.librarify.config.user;

import com.leonardorossi.librarify.application.user.gateways.UserRepository;
import com.leonardorossi.librarify.application.user.usecase.CreateUserUseCase;
import com.leonardorossi.librarify.application.user.usecase.FindAllUsersUseCase;
import com.leonardorossi.librarify.application.user.usecase.FindOneUserUseCase;
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
  CreateUserUseCase createUserUserCase(UserRepository repository) {
    return new CreateUserUseCase(repository);
  }
  
  @Bean
  FindOneUserUseCase findOneUserUseCase(UserRepository repository) {
    return new FindOneUserUseCase(repository);
  }
  
  @Bean
  FindAllUsersUseCase findAllUsersUseCase(UserRepository repository) {
    return new FindAllUsersUseCase(repository);
  }
  
  @Bean
  UserRepositoryAdapter userRepositoryAdapter(
      UserJpaRepository repository, UserEntityMapper mapper
  ) {
    return new UserRepositoryAdapter(repository, mapper);
  }
  
}
