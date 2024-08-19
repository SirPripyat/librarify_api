package com.leonardorossi.librarify.application.user.usecase;

import com.leonardorossi.librarify.application.user.gateways.UserRepository;
import com.leonardorossi.librarify.domain.user.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindAllUsersUseCaseTest {
  
  @Mock
  private UserRepository userRepository;
  
  @InjectMocks
  private FindAllUsersUseCase findAllUsersUseCase;
 
  @Test
  void findAll_shouldReturnPaginatedUsers() {
    Pageable pageable = PageRequest.of(0, 6);
    List<User> users = List.of(
      User.builder().id(1L).name("User1").build(),
      User.builder().id(2L).name("User2").build(),
      User.builder().id(3L).name("User3").build(),
      User.builder().id(4L).name("User4").build(),
      User.builder().id(5L).name("User5").build(),
      User.builder().id(6L).name("User6").build()
    );
    Page<User> paginatedUsers = new PageImpl<>(users, pageable, users.size());
    
    when(userRepository.findAll(pageable)).thenReturn(paginatedUsers);
    
    Page<User> result = findAllUsersUseCase.findAll(pageable);
    
    assertEquals(6, result.getTotalElements());
    assertEquals(6, result.getContent().size());
    assertEquals("User1", result.getContent().getFirst().getName());
    assertEquals("User6", result.getContent().getLast().getName());
  }
}
