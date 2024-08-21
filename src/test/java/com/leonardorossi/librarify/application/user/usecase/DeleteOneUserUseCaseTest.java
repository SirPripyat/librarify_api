package com.leonardorossi.librarify.application.user.usecase;

import com.leonardorossi.librarify.application.user.gateways.UserRepository;
import com.leonardorossi.librarify.domain.user.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteOneUserUseCaseTest {
  
  @Mock
  private UserRepository userRepository;
  
  @Mock
  private FindOneUserUseCase findOneUserUseCase;
  
  @InjectMocks
  private DeleteOneUserUseCase deleteOneUserUseCase;
  
  @Test
  void shouldToggleUserStatusToFalse() {
    Long userId = 1L;
    User existingUser = User.builder()
      .id(userId)
      .status(true)
      .build();
    
    when(findOneUserUseCase.execute(userId)).thenReturn(existingUser);
    when(userRepository.save(existingUser)).thenReturn(existingUser);
    
    User updatedUser = deleteOneUserUseCase.execute(userId);
    
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
    
    when(findOneUserUseCase.execute(userId)).thenReturn(existingUser);
    when(userRepository.save(existingUser)).thenReturn(existingUser);
    
    User updatedUser = deleteOneUserUseCase.execute(userId);
    
    assertEquals(true, updatedUser.getStatus());
    verify(userRepository, times(1)).save(existingUser);
  }
  
}
