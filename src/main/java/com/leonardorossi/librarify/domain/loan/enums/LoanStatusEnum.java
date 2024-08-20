package com.leonardorossi.librarify.domain.loan.enums;

import lombok.Getter;

/**
 * Enum que representa o status de um empréstimo.
 */
@Getter
public enum LoanStatusEnum {
  PENDING("PENDENTE"),
  RETURNED("DEVOLVIDO"),
  LATE("ATRASADO");
  
  private final String description;
  
  LoanStatusEnum(String description) {
    this.description = description;
  }
}
