package com.leonardorossi.librarify.config.user;

import com.leonardorossi.librarify.application.user.gateways.UserRepository;
import com.leonardorossi.librarify.application.user.usecase.*;
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
  DeleteOneUserUseCase deleteOneUserUseCase(
      UserRepository userRepository, FindOneUserUseCase findOneUserUseCase
  ) {
    return new DeleteOneUserUseCase(userRepository, findOneUserUseCase);
  }
  
  @Bean
  UpdateOneUserUseCase updateOneUserUseCase(
      UserRepository userRepository
  ) {
    return new UpdateOneUserUseCase(userRepository);
  }
  
  @Bean
  UserRepositoryAdapter userRepositoryAdapter(
      UserJpaRepository repository, UserEntityMapper mapper
  ) {
    return new UserRepositoryAdapter(repository, mapper);
  }
  
}
