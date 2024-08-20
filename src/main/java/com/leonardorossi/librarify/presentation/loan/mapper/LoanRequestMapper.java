package com.leonardorossi.librarify.presentation.loan.mapper;

import com.leonardorossi.librarify.domain.loan.entity.Loan;
import com.leonardorossi.librarify.presentation.book.mapper.BookRequestMapper;
import com.leonardorossi.librarify.presentation.loan.dtos.CreateLoanRequestDto;
import com.leonardorossi.librarify.presentation.user.mapper.UserRequestMapper;
import org.springframework.stereotype.Component;

/**
 * Converte DTOs de requisição para entidades de livro.
 */
@Component
public class LoanRequestMapper {
  
  private final UserRequestMapper userRequestMapper;
  private final BookRequestMapper bookRequestMapper;
  
  public LoanRequestMapper(
      UserRequestMapper userRequestMapper,
      BookRequestMapper bookRequestMapper
  ) {
    this.userRequestMapper = userRequestMapper;
    this.bookRequestMapper = bookRequestMapper;
  }
  
  /**
   * Converte um {@link CreateLoanRequestDto} para um {@link Loan}.
   *
   * @param requestDto Dados de entrada para criar um usuário.
   * @return A entidade {@link Loan} criada.
   */
  public Loan toEntity(CreateLoanRequestDto requestDto) {
    return Loan.builder()
      .user(userRequestMapper.toEntity(requestDto.idUser()))
      .book(bookRequestMapper.toEntity(requestDto.idBook()))
      .loanDate(requestDto.loanDate())
      .returnDate(requestDto.returnDate())
      .loanStatus(requestDto.status())
      .build();
  }
}
