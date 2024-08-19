package com.leonardorossi.librarify.presentation.user;

import com.leonardorossi.librarify.application.user.usecase.CreateUserUseCase;
import com.leonardorossi.librarify.application.user.usecase.FindAllUsersUseCase;
import com.leonardorossi.librarify.application.user.usecase.FindOneUserUseCase;
import com.leonardorossi.librarify.domain.user.entity.User;
import com.leonardorossi.librarify.presentation.user.dtos.CreateUserRequestDto;
import com.leonardorossi.librarify.presentation.user.mapper.UserRequestMapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável por gerenciar as operações relacionadas aos usuários.
 * Esta classe contém endpoints para operação de CRUD dos usuários.
 */
@RestController
@RequestMapping("/user")
public class UserController {
  
  private final CreateUserUseCase createUserService;
  private final FindOneUserUseCase findOneUserUseCase;
  private final FindAllUsersUseCase findAllUsersUseCase;
  private final UserRequestMapper userMapper;
  
  /**
   * Construtor da classe.
   */
  public UserController(
      CreateUserUseCase createUserService,
      FindOneUserUseCase findOneUserUseCase,
      FindAllUsersUseCase findAllUsersUseCase,
      UserRequestMapper userMapper
  ) {
    this.createUserService = createUserService;
    this.findOneUserUseCase = findOneUserUseCase;
    this.findAllUsersUseCase = findAllUsersUseCase;
    this.userMapper = userMapper;
  }
  
  @PostMapping("/create")
  public ResponseEntity<User> create(@Valid @RequestBody CreateUserRequestDto requestDto) {
    User userEntity = userMapper.toEntity(requestDto);
    return ResponseEntity.ok(createUserService.create(userEntity));
  }
  
  @GetMapping("/find-by-id/{id}")
  public ResponseEntity<User> findById(@PathVariable Long id) {
    return ResponseEntity.ok(findOneUserUseCase.findById(id));
  }
  
  @GetMapping("/find-all")
  public ResponseEntity<Page<User>> findAll(Pageable pageable) {
    return ResponseEntity.ok(findAllUsersUseCase.findAll(pageable));
  }
  
}
