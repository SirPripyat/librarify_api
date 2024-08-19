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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteOneUserUseCaseTest {
  
  @Mock
  private UserRepository userRepository;
  
  @InjectMocks
  private DeleteOneUserUseCase deleteOneUserUseCase;
  
  @Test
  void shouldToggleUserStatusToFalse() {
    Long userId = 1L;
    User existingUser = User.builder()
      .id(userId)
      .status(true)
      .build();
    
    when(userRepository.findOneById(userId)).thenReturn(Optional.of(existingUser));
    when(userRepository.save(existingUser)).thenReturn(existingUser);
    
    User updatedUser = deleteOneUserUseCase.toggleStatus(userId);
    
    assertEquals(false, updatedUser.getStatus());
    verify(userRepository, times(1)).save(existingUser);
  }
  
  @Test
  void shouldToggleUserStatusToTrue() {
    Long userId = 1L;
    User existingUser = User.builder()
      .id(userId)
      .status(false)
      .build();
    
    when(userRepository.findOneById(userId)).thenReturn(Optional.of(existingUser));
    when(userRepository.save(existingUser)).thenReturn(existingUser);
    
    User updatedUser = deleteOneUserUseCase.toggleStatus(userId);
    
    assertEquals(true, updatedUser.getStatus());
    verify(userRepository, times(1)).save(existingUser);
  }
  
  @Test
  void shouldThrowExceptionWhenUserNotFound() {
    Long idUser = 12324L;
    
    when(userRepository.findOneById(idUser)).thenReturn(Optional.empty());
    
    CustomBadRequestException exception = assertThrows(CustomBadRequestException.class, () ->
      deleteOneUserUseCase.toggleStatus(idUser)
    );
    
    assertEquals(
      String.format(UserExceptionMessages.USER_NOT_FOUND, idUser), exception.getMessage()
    );
    
  }
}
