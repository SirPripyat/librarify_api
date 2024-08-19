package com.leonardorossi.librarify.config.book;

import com.leonardorossi.librarify.application.book.gateways.BookRepository;
import com.leonardorossi.librarify.application.book.usecase.CreateBookUseCase;
import com.leonardorossi.librarify.application.book.usecase.FindAllBookUseCase;
import com.leonardorossi.librarify.application.book.usecase.FindOneBookUseCase;
import com.leonardorossi.librarify.application.book.usecase.ValidateIsbnUseCase;
import com.leonardorossi.librarify.infra.book.gateways.BookRepositoryAdapter;
import com.leonardorossi.librarify.infra.book.mapper.BookMapper;
import com.leonardorossi.librarify.infra.book.persistence.BookJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração para beans relacionados a livros.
 */
@Configuration
public class BookConfig {
  @Bean
  CreateBookUseCase createBookRequestDto(
      BookRepository bookRepository,
      ValidateIsbnUseCase validateIsbnUseCase
  ) {
    return new CreateBookUseCase(
      bookRepository,
      validateIsbnUseCase
    );
  }
  
  @Bean
  ValidateIsbnUseCase validateIsbnUseCase() {
    return new ValidateIsbnUseCase();
  }
  
  @Bean
  FindOneBookUseCase findOneBookUseCase(BookRepository bookRepository) {
    return new FindOneBookUseCase(bookRepository);
  }
  
  @Bean
  FindAllBookUseCase findAllBookUseCase(BookRepository bookRepository) {
    return new FindAllBookUseCase(bookRepository);
  }
  
  @Bean
  BookRepositoryAdapter bookRepositoryAdapter(
      BookJpaRepository bookJpaRepository, BookMapper bookMapper
  ) {
    return new BookRepositoryAdapter(bookJpaRepository, bookMapper);
  }
}
