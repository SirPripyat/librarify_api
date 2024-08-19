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
public class UpdateOneUserUseCaseTest {
  
  @Mock
  private UserRepository userRepository;
  
  @Mock
  private FindOneUserUseCase findOneUserUseCase;
  
  @InjectMocks
  private UpdateOneUserUseCase updateOneUserUseCase;
  
  @Test
  void shouldUpdateUser() {
    Long idUser = 1L;
    
    User existingUser = User.builder()
      .id(idUser)
      .name("Carlos")
      .email("carlos@mail.com")
      .registrationDate(LocalDate.now())
      .phone("123456789")
      .status(true)
      .build();
    
    User userToUpdate = User.builder()
      .name("Manoel Gomes")
      .email("manoel@mail.com")
      .build();
    
    when(findOneUserUseCase.findById(idUser)).thenReturn(existingUser);
    when(userRepository.existsByEmail(userToUpdate.getEmail())).thenReturn(false);
    when(userRepository.save(existingUser)).thenReturn(existingUser);
    
    User updatedUser = updateOneUserUseCase.update(idUser, userToUpdate);
    
    assertEquals("Manoel Gomes", updatedUser.getName());
    assertEquals("manoel@mail.com", updatedUser.getEmail());
    
    verify(findOneUserUseCase, times(1)).findById(idUser);
    verify(userRepository).existsByEmail(existingUser.getEmail());
    verify(userRepository, times(1)).save(existingUser);
  }
  
  @Test
  void shouldThrowCustomBadRequestExceptionWhenEmailAlreadyExists() {
    Long idUser = 1L;
    
    User existingUser = User.builder()
      .id(idUser)
      .name("Carlos")
      .email("carlos@mail.com")
      .registrationDate(LocalDate.now())
      .phone("123456789")
      .status(true)
      .build();
    
    User userToUpdate = User.builder()
      .name("Manoel Gomes")
      .email("manoel.gomes@mail.com")
      .build();
    
    when(findOneUserUseCase.findById(idUser)).thenReturn(existingUser);
    when(userRepository.existsByEmail(userToUpdate.getEmail())).thenReturn(true);
    
    CustomBadRequestException exception = assertThrows(CustomBadRequestException.class, () ->
      updateOneUserUseCase.update(idUser, userToUpdate)
    );
    
    assertEquals(
      String.format(UserExceptionMessages.EMAIL_ALREADY_EXISTS, userToUpdate.getEmail()),
      exception.getMessage()
    );
    
    verify(userRepository, never()).save(any(User.class));
  }
}
