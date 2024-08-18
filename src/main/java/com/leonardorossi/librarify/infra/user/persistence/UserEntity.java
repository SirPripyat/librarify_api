package com.leonardorossi.librarify.infra.user.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade que representa o usuário na base de dados.
 * Mapeada para a tabela "USUARIO".
 */
@Entity
@Table(name = "USUARIO")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID_USUARIO")
  private Long id;
  
  @Column(name = "NOME", nullable = false)
  private String name;
  
  @Column(name = "EMAIL", nullable = false, unique = true)
  private String email;
  
  @Column(name = "DATA_CADASTRO", nullable = false)
  private LocalDate registrationDate;
  
  @Column(name = "TELEFONE", nullable = false)
  private String phone;
  
  @Column(name = "STATUS", nullable = false)
  private Boolean status;
}
