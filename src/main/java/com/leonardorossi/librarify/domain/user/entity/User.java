package com.leonardorossi.librarify.domain.user.entity;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade de domínio que representa um usuário.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
  private Long id;
  private String name;
  private String email;
  private LocalDate registrationDate;
  private String phone;
  private Boolean status;
}
