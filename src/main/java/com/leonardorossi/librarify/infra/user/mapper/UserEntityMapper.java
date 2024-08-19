package com.leonardorossi.librarify.infra.user.mapper;

import com.leonardorossi.librarify.domain.user.entity.User;
import com.leonardorossi.librarify.infra.user.persistence.UserEntity;
import org.springframework.stereotype.Component;

/**
 * Mapper responsável por converter entre a entidade de domínio {@link User} e a entidade de
 * persistência {@link UserEntity}.
 */
@Component
public class UserEntityMapper {
  
  /**
   * Converte um {@link User} para um {@link UserEntity}.
   *
   * @param user Entidade de domínio {@link User}.
   * @return Entidade de persistência {@link UserEntity}.
   */
  public UserEntity toEntity(User user) {
    return UserEntity.builder()
      .id(user.getId())
      .name(user.getName())
      .email(user.getEmail())
      .registrationDate(user.getRegistrationDate())
      .phone(user.getPhone())
      .status(user.getStatus())
      .build();
  }
  
  /**
   * Converte um {@link UserEntity} para um {@link User}.
   *
   * @param userEntity Entidade de persistência {@link UserEntity}.
   * @return Entidade de domínio {@link User}.
   */
  public User toDomain(UserEntity userEntity) {
    return User.builder()
      .id(userEntity.getId())
      .name(userEntity.getName())
      .email(userEntity.getEmail())
      .registrationDate(userEntity.getRegistrationDate())
      .phone(userEntity.getPhone())
      .status(userEntity.getStatus())
      .build();
  }
  
}
