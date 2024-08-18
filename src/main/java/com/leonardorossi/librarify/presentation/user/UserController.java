package com.leonardorossi.librarify.presentation.user;

import com.leonardorossi.librarify.application.user.usecase.CreateUserUserCase;
import com.leonardorossi.librarify.domain.user.entity.User;
import com.leonardorossi.librarify.presentation.user.dtos.CreateUserRequestDto;
import com.leonardorossi.librarify.presentation.user.mapper.UserRequestMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável por gerenciar as operações relacionadas aos usuários.
 * Esta classe contém endpoints para operação de CRUD dos usuários.
 */
@RestController
@RequestMapping("/users")
public class UserController {
  
  private final CreateUserUserCase createUserService;
  private final UserRequestMapper userMapper;
  
  public UserController(CreateUserUserCase createUserService, UserRequestMapper userMapper) {
    this.createUserService = createUserService;
    this.userMapper = userMapper;
  }
  
  @PostMapping("/create")
  public ResponseEntity<User> create(@Valid @RequestBody CreateUserRequestDto requestDto) {
    User userEntity = userMapper.toEntity(requestDto);
    return ResponseEntity.ok(createUserService.create(userEntity));
  }
  
}
