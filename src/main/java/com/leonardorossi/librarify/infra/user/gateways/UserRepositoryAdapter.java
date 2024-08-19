package com.leonardorossi.librarify.infra.user.gateways;

import com.leonardorossi.librarify.application.user.gateways.UserRepository;
import com.leonardorossi.librarify.domain.user.entity.User;
import com.leonardorossi.librarify.infra.user.mapper.UserEntityMapper;
import com.leonardorossi.librarify.infra.user.persistence.UserEntity;
import com.leonardorossi.librarify.infra.user.persistence.UserJpaRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Adaptador que implementa o repositório de usuários para conectar a camada de domínio com a
 * infraestrutura.
 * Este adaptador converte entre entidades de domínio e entidades persistidas.
 */
public class UserRepositoryAdapter implements UserRepository {
  
  private final UserJpaRepository repository;
  private final UserEntityMapper mapper;
  
  public UserRepositoryAdapter(UserJpaRepository repository, UserEntityMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }
  
  @Override
  public User save(User user) {
    UserEntity entity = mapper.toEntity(user);
    
    return mapper.toDomain(repository.save(entity));
  }
  
  @Override
  public boolean existsByEmail(String email) {
    return repository.existsByEmail(email);
  }
  
  @Override
  public Optional<User> findOneById(Long id) {
    Optional<UserEntity> userEntity = repository.findOneByIdAndStatusIsTrue(id);
    
    return userEntity.map(mapper::toDomain);
  }
  
  @Override
  public Page<User> findAll(Pageable pageable) {
    Page<UserEntity> userEntities = repository.findAll(pageable);
    return userEntities.map(mapper::toDomain);
  }
  
}
