package com.leonardorossi.librarify.presentation.book;

import com.leonardorossi.librarify.application.book.usecase.CreateBookUseCase;
import com.leonardorossi.librarify.domain.book.entity.Book;
import com.leonardorossi.librarify.presentation.book.dtos.CreateBookRequestDto;
import com.leonardorossi.librarify.presentation.book.mapper.BookRequestMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável por gerenciar as operações relacionadas aos livros.
 * Esta classe contém endpoints para operação de CRUD dos livros.
 */
@RestController
@RequestMapping("/book")
public class BookController {
  
  private final CreateBookUseCase createBookUseCase;
  
  private final BookRequestMapper mapper;
  
  public BookController(
      CreateBookUseCase createBookUseCase,
      BookRequestMapper mapper
  ) {
    this.createBookUseCase = createBookUseCase;
    this.mapper = mapper;
  }
  
  @PostMapping("/create")
  public ResponseEntity<Book> createBook(
      @Valid @RequestBody CreateBookRequestDto requestDto
  ) {
    Book book = mapper.toEntity(requestDto);
    return ResponseEntity.ok(createBookUseCase.create(book));
  }
}
