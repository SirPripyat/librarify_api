package com.leonardorossi.librarify.config.book;

import com.leonardorossi.librarify.application.book.gateways.BookRepository;
import com.leonardorossi.librarify.application.book.usecase.CreateBookUseCase;
import com.leonardorossi.librarify.application.book.usecase.DeleteOneBookUseCase;
import com.leonardorossi.librarify.application.book.usecase.FindAllBooksUseCase;
import com.leonardorossi.librarify.application.book.usecase.FindOneBookUseCase;
import com.leonardorossi.librarify.application.book.usecase.UpdateOneBookUseCase;
import com.leonardorossi.librarify.infra.book.gateways.BookRepositoryAdapter;
import com.leonardorossi.librarify.infra.book.mapper.BookEntityMapper;
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
      BookRepository bookRepository
  ) {
    return new CreateBookUseCase(
      bookRepository
    );
  }
  
  @Bean
  FindOneBookUseCase findOneBookUseCase(BookRepository bookRepository) {
    return new FindOneBookUseCase(bookRepository);
  }
  
  @Bean
  FindAllBooksUseCase findAllBookUseCase(BookRepository bookRepository) {
    return new FindAllBooksUseCase(bookRepository);
  }
  
  @Bean
  UpdateOneBookUseCase updateOneBookUseCase(
      BookRepository bookRepository,
      FindOneBookUseCase findOneBookUseCase
  ) {
    return new UpdateOneBookUseCase(
      bookRepository,
      findOneBookUseCase
    );
  }
  
  @Bean
  DeleteOneBookUseCase deleteOneBookUseCase(
      BookRepository bookRepository,
      FindOneBookUseCase findOneBookUseCase
  ) {
    return new DeleteOneBookUseCase(
      bookRepository,
      findOneBookUseCase
    );
  }
  
  @Bean
  BookRepositoryAdapter bookRepositoryAdapter(
      BookJpaRepository bookJpaRepository, BookEntityMapper bookMapper
  ) {
    return new BookRepositoryAdapter(bookJpaRepository, bookMapper);
  }
}
