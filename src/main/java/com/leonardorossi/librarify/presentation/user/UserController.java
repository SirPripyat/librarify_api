package com.leonardorossi.librarify.presentation.user;

import com.leonardorossi.librarify.application.user.usecase.CreateUserUseCase;
import com.leonardorossi.librarify.application.user.usecase.DeleteOneUserUseCase;
import com.leonardorossi.librarify.application.user.usecase.FindAllUsersUseCase;
import com.leonardorossi.librarify.application.user.usecase.FindOneUserUseCase;
import com.leonardorossi.librarify.application.user.usecase.UpdateOneUserUseCase;
import com.leonardorossi.librarify.domain.user.entity.User;
import com.leonardorossi.librarify.presentation.user.dtos.CreateUserRequestDto;
import com.leonardorossi.librarify.presentation.user.dtos.UpdateUserRequestDto;
import com.leonardorossi.librarify.presentation.user.mapper.UserRequestMapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
 * Controlador responsável por gerenciar as operações relacionadas aos usuários.
 * Esta classe contém endpoints para operação de CRUD dos usuários.
 */
@RestController
@RequestMapping("/user")
public class UserController {
  
  private final CreateUserUseCase createUserUseCase;
  private final FindOneUserUseCase findOneUserUseCase;
  private final FindAllUsersUseCase findAllUsersUseCase;
  private final DeleteOneUserUseCase deleteOneUserUseCase;
  private final UpdateOneUserUseCase updateOneUserUseCase;
  
  private final UserRequestMapper userMapper;
  
  /**
   * Construtor da classe.
   *
   * @param createUserUseCase Caso de uso responsável pela criação de um usuário.
   * @param findOneUserUseCase Caso de uso responsável por buscar um usuário pelo seu ID.
   * @param findAllUsersUseCase Caso de uso responsável por buscar todos os usuários com paginação.
   * @param deleteOneUserUseCase Caso de uso responsável por excluir um usuário.
   * @param updateOneUserUseCase Caso de uso responsável por atualizar os detalhes de um usuário.
   * @param userMapper Mapper para converter os DTOs em entidades.
   */
  public UserController(
      CreateUserUseCase createUserUseCase,
      FindOneUserUseCase findOneUserUseCase,
      FindAllUsersUseCase findAllUsersUseCase,
      DeleteOneUserUseCase deleteOneUserUseCase,
      UpdateOneUserUseCase updateOneUserUseCase,
      UserRequestMapper userMapper
  ) {
    this.createUserUseCase = createUserUseCase;
    this.findOneUserUseCase = findOneUserUseCase;
    this.findAllUsersUseCase = findAllUsersUseCase;
    this.deleteOneUserUseCase = deleteOneUserUseCase;
    this.updateOneUserUseCase = updateOneUserUseCase;
    this.userMapper = userMapper;
  }
  
  /**
   * Endpoint para criar um novo usuário.
   *
   * @param requestDto Dados do usuário a ser criado.
   * @return O usuário criado.
   */
  @PostMapping("/create")
  public ResponseEntity<User> create(@Valid @RequestBody CreateUserRequestDto requestDto) {
    User user = userMapper.toEntity(requestDto);
    return ResponseEntity.ok(createUserUseCase.execute(user));
  }
  
  /**
   * Endpoint para buscar um usuário pelo seu ID.
   *
   * @param id ID do usuário a ser buscado.
   * @return O usuário correspondente ao ID informado.
   */
  @GetMapping("/find-by-id/{id}")
  public ResponseEntity<User> findById(@PathVariable Long id) {
    return ResponseEntity.ok(findOneUserUseCase.execute(id));
  }
  
  /**
   * Endpoint para buscar todos os usuários com paginação.
   *
   * @param pageable Configuração de paginação.
   * @return Uma página com os usuários encontrados.
   */
  @GetMapping("/find-all")
  public ResponseEntity<Page<User>> findAll(Pageable pageable) {
    return ResponseEntity.ok(findAllUsersUseCase.execute(pageable));
  }
  
  /**
   * Endpoint para excluir um usuário pelo seu ID.
   *
   * @param id ID do usuário a ser excluído.
   * @return O usuário excluído.
   */
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<User> delete(@PathVariable Long id) {
    return ResponseEntity.ok(deleteOneUserUseCase.execute(id));
  }
  
  /**
   * Endpoint para atualizar os detalhes de um usuário.
   *
   * @param id         ID do usuário a ser atualizado.
   * @param requestDto Dados atualizados do usuário.
   * @return O usuário atualizado.
   */
  @PutMapping("/update/{id}")
  public ResponseEntity<User> update(
      @PathVariable Long id,
      @Valid @RequestBody UpdateUserRequestDto requestDto
  ) {
    User userEntity = userMapper.toEntity(requestDto);
    return ResponseEntity.ok(updateOneUserUseCase.execute(id, userEntity));
  }
}
