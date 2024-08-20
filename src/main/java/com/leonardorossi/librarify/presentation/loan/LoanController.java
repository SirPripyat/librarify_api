package com.leonardorossi.librarify.presentation.loan;

import com.leonardorossi.librarify.application.loan.usecase.CheckOutOneBookUseCase;
import com.leonardorossi.librarify.domain.loan.entity.Loan;
import com.leonardorossi.librarify.presentation.loan.dtos.CreateLoanRequestDto;
import com.leonardorossi.librarify.presentation.loan.mapper.LoanRequestMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável por gerenciar as operações relacionadas aos empréstimo.
 */
@RestController
@RequestMapping("/loan")
public class LoanController {
  
  private final CheckOutOneBookUseCase checkOutOneBookUseCase;
  
  private final LoanRequestMapper mapper;
  
  public LoanController(
      CheckOutOneBookUseCase checkOutOneBookUseCase,
      LoanRequestMapper mapper
  ) {
    this.checkOutOneBookUseCase = checkOutOneBookUseCase;
    this.mapper = mapper;
  }
  
  @PostMapping("/create")
  public ResponseEntity<Loan> createLoan(
      @Valid @RequestBody CreateLoanRequestDto requestDto
  ) {
    Loan loan = mapper.toEntity(requestDto);
    return ResponseEntity.ok(checkOutOneBookUseCase.execute(loan));
  }
}
