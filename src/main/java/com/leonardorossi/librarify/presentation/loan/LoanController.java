package com.leonardorossi.librarify.presentation.loan;

import com.leonardorossi.librarify.application.loan.usecase.CheckOutOneBookUseCase;
import com.leonardorossi.librarify.application.loan.usecase.FindLastBooksCheckedOutByUserUseCase;
import com.leonardorossi.librarify.application.loan.usecase.UpdateOneLoanUseCase;
import com.leonardorossi.librarify.domain.book.entity.Book;
import com.leonardorossi.librarify.domain.loan.entity.Loan;
import com.leonardorossi.librarify.presentation.loan.dtos.CreateLoanRequestDto;
import com.leonardorossi.librarify.presentation.loan.dtos.UpdateLoanRequestDto;
import com.leonardorossi.librarify.presentation.loan.mapper.LoanRequestMapper;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  private final UpdateOneLoanUseCase updateOneLoanUseCase;
  private final FindLastBooksCheckedOutByUserUseCase findLastBooksCheckedOutByUserUseCase;
  
  private final LoanRequestMapper mapper;
  
  /**
   * Construtor da classe.
   *
   * @param checkOutOneBookUseCase              Caso de uso responsável por criar um
   *                                            empréstimo de livro.
   * @param updateOneLoanUseCase                Caso de uso responsável por atualizar os
   *                                            detalhes de um empréstimo.
   * @param findLastBooksCheckedOutByUserUseCase Caso de uso responsável por buscar os últimos
   *                                             livros emprestados por um usuário.
   * @param mapper                              Mapper para converter os DTOs em entidades.
   */
  public LoanController(
      CheckOutOneBookUseCase checkOutOneBookUseCase,
      UpdateOneLoanUseCase updateOneLoanUseCase,
      FindLastBooksCheckedOutByUserUseCase findLastBooksCheckedOutByUserUseCase,
      LoanRequestMapper mapper
  ) {
    this.checkOutOneBookUseCase = checkOutOneBookUseCase;
    this.updateOneLoanUseCase = updateOneLoanUseCase;
    this.findLastBooksCheckedOutByUserUseCase = findLastBooksCheckedOutByUserUseCase;
    this.mapper = mapper;
  }
  
  /**
   * Endpoint para criar um novo empréstimo.
   *
   * @param requestDto Dados do empréstimo a ser criado.
   * @return O empréstimo criado.
   */
  @PostMapping("/create")
  public ResponseEntity<Loan> createLoan(
      @Valid @RequestBody CreateLoanRequestDto requestDto
  ) {
    Loan loan = mapper.toEntity(requestDto);
    return ResponseEntity.ok(checkOutOneBookUseCase.execute(loan));
  }
  
  /**
   * Endpoint para buscar os últimos livros emprestados por um usuário.
   *
   * @param idUser ID do usuário.
   * @return A lista dos últimos livros emprestados pelo usuário.
   */
  @GetMapping("/last/{idUser}")
  public ResponseEntity<List<Book>> findLastBooksCheckOutByUser(
      @PathVariable Long idUser
  ) {
    return ResponseEntity.ok(findLastBooksCheckedOutByUserUseCase.find(idUser));
  }
  
  /**
   * Endpoint para atualizar os detalhes de um empréstimo.
   *
   * @param id         ID do empréstimo a ser atualizado.
   * @param requestDto Dados atualizados do empréstimo.
   * @return O empréstimo atualizado.
   */
  @PutMapping("/update/{id}")
  public ResponseEntity<Loan> updateLoan(
      @PathVariable Long id,
      @Valid @RequestBody UpdateLoanRequestDto requestDto
  ) {
    Loan loan = mapper.toEntity(requestDto);
    return ResponseEntity.ok(updateOneLoanUseCase.update(id, loan));
  }
}
