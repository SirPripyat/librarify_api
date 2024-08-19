package com.leonardorossi.librarify.application.user.usecase;

import com.leonardorossi.librarify.application.user.gateways.UserRepository;
import com.leonardorossi.librarify.domain.user.entity.User;
import com.leonardorossi.librarify.infra.exception.CustomBadRequestException;
import com.leonardorossi.librarify.presentation.user.messages.UserExceptionMessages;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateUserUseCaseTest {
  
  @Mock
  private UserRepository userRepository;
  
  @InjectMocks
  private CreateUserUseCase createUserUseCase;
  
  @Test
  void shouldCreateUserWhenEmailDoesNotExist() {
    User user = new User(
      "Carlos",
      "carlos@mail.com",
      LocalDate.now(),
      "123456789",
      true);
    
    when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
    when(userRepository.create(user)).thenReturn(user);
    
    User createdUser = createUserUseCase.create(user);
    
    assertEquals(user, createdUser);
    verify(userRepository, times(1)).create(user);
  }
  
  @Test
  void shouldThrowCustomBadRequestExceptionWhenEmailAlreadyExists() {
    User user = new User(
      "Carlos",
      "carlos@mail.com",
      LocalDate.now(),
      "123456789",
      true);
    
    when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);
    
    CustomBadRequestException exception = assertThrows(CustomBadRequestException.class, () ->
      createUserUseCase.create(user)
    );

    assertEquals(String.format(UserExceptionMessages.EMAIL_ALREADY_EXISTS, user.getEmail()),
      exception.getMessage());
    verify(userRepository, never()).create(any(User.class));
  }
  
}
