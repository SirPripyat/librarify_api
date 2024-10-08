package com.leonardorossi.librarify.presentation.book;

import com.leonardorossi.librarify.application.book.usecase.CreateBookUseCase;
import com.leonardorossi.librarify.application.book.usecase.DeleteOneBookUseCase;
import com.leonardorossi.librarify.application.book.usecase.FindAllBooksUseCase;
import com.leonardorossi.librarify.application.book.usecase.FindOneBookUseCase;
import com.leonardorossi.librarify.application.book.usecase.UpdateOneBookUseCase;
import com.leonardorossi.librarify.domain.book.entity.Book;
import com.leonardorossi.librarify.presentation.book.dtos.CreateBookRequestDto;
import com.leonardorossi.librarify.presentation.book.dtos.UpdateBookRequestDto;
import com.leonardorossi.librarify.presentation.book.mapper.BookRequestMapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  private final FindOneBookUseCase findOneBookUseCase;
  private final FindAllBooksUseCase findAllBooksUseCase;
  private final UpdateOneBookUseCase updateOneBookUseCase;
  private final DeleteOneBookUseCase deleteOneBookUseCase;
  
  private final BookRequestMapper mapper;
  
  /**
   * Construtor da classe.
   *
   * @param createBookUseCase    Caso de uso responsável pela criação de um livro.
   * @param findOneBookUseCase   Caso de uso responsável por buscar um livro pelo seu ID.
   * @param findAllBooksUseCase  Caso de uso responsável por buscar todos os livros com paginação.
   * @param updateOneBookUseCase Caso de uso responsável por atualizar os detalhes de um livro.
   * @param deleteOneBookUseCase Caso de uso responsável por excluir um livro.
   * @param mapper               Mapper para converter os DTOs em entidades.
   */
  public BookController(
      CreateBookUseCase createBookUseCase,
      FindOneBookUseCase findOneBookUseCase,
      FindAllBooksUseCase findAllBooksUseCase,
      UpdateOneBookUseCase updateOneBookUseCase,
      DeleteOneBookUseCase deleteOneBookUseCase,
      BookRequestMapper mapper
  ) {
    this.createBookUseCase = createBookUseCase;
    this.findOneBookUseCase = findOneBookUseCase;
    this.findAllBooksUseCase = findAllBooksUseCase;
    this.updateOneBookUseCase = updateOneBookUseCase;
    this.deleteOneBookUseCase = deleteOneBookUseCase;
    this.mapper = mapper;
  }
  
  /**
   * Endpoint para criar um novo livro.
   *
   * @param requestDto Dados do livro a ser criado.
   * @return O livro criado com status 201 CREATED.
   */
  @PostMapping("/create")
  public ResponseEntity<Book> createBook(
      @Valid @RequestBody CreateBookRequestDto requestDto
  ) {
    Book book = mapper.toEntity(requestDto);
    Book createdBook = createBookUseCase.execute(book);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
  }
  
  /**
   * Endpoint para buscar um livro pelo seu ID.
   *
   * @param id ID do livro a ser buscado.
   * @return O livro correspondente ao ID informado.
   */
  @GetMapping("/find-by-id/{id}")
  public ResponseEntity<Book> findOneById(
      @PathVariable Long id
  ) {
    return ResponseEntity.ok(findOneBookUseCase.execute(id));
  }
  
  /**
   * Endpoint para buscar todos os livros com paginação.
   *
   * @param pageable Configuração de paginação.
   * @return Uma página com os livros encontrados.
   */
  @GetMapping("/find-all")
  public ResponseEntity<Page<Book>> findAll(
      Pageable pageable
  ) {
    return ResponseEntity.ok(findAllBooksUseCase.execute(pageable));
  }
  
  /**
   * Endpoint para excluir um livro pelo seu ID.
   *
   * @param id ID do livro a ser excluído.
   * @return O livro excluído.
   */
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Book> deleteBook(
      @PathVariable Long id
  ) {
    return ResponseEntity.ok(deleteOneBookUseCase.execute(id));
  }
  
  /**
   * Endpoint para atualizar os detalhes de um livro.
   *
   * @param id         ID do livro a ser atualizado.
   * @param requestDto Dados atualizados do livro.
   * @return O livro atualizado.
   */
  @PutMapping("/update/{id}")
  public ResponseEntity<Book> updateBook(
      @PathVariable Long id,
      @Valid @RequestBody UpdateBookRequestDto requestDto
  ) {
    Book book = mapper.toEntity(requestDto);
    return ResponseEntity.ok(updateOneBookUseCase.execute(id, book));
  }
  
}
