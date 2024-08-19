package com.leonardorossi.librarify.application.user.usecase;

import com.leonardorossi.librarify.application.user.gateways.UserRepository;
import com.leonardorossi.librarify.domain.user.entity.User;
import com.leonardorossi.librarify.infra.exception.CustomBadRequestException;
import com.leonardorossi.librarify.presentation.user.messages.UserExceptionMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindOneActiveUserUseCaseTest {
  
  @Mock
  private UserRepository userRepository;
  
  @InjectMocks
  private FindOneActiveUser findOneUserUseCase;
  
  private User mockUser;
  
  @BeforeEach
  void setUp() {
    mockUser = User.builder()
      .id(1L)
      .name("Carlos")
      .build();
  }
  
  @Test
  void shouldReturnAValidUserWhenIdExists() {
    long idUser = 1L;
    
    when(userRepository.findOneByIdAndStatusIsTrue(idUser)).thenReturn(Optional.of(mockUser));
    
    User result = findOneUserUseCase.findById(idUser);
    
    assertEquals(mockUser, result);
  }
  
  @Test
  void shouldThrowExceptionWhenIdDoesNotExist() {
    long idUser = 1L;
    
    when(userRepository.findOneByIdAndStatusIsTrue(idUser)).thenReturn(Optional.empty());
    
    CustomBadRequestException exception = assertThrows(CustomBadRequestException.class, () ->
      findOneUserUseCase.findById(idUser)
    );
    
    assertEquals(
      String.format(UserExceptionMessages.USER_NOT_FOUND, idUser), exception.getMessage()
    );
  }
  
}
