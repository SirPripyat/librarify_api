package com.leonardorossi.librarify.infra.book.gateways;

import com.leonardorossi.librarify.application.book.gateways.BookRepository;
import com.leonardorossi.librarify.domain.book.entity.Book;
import com.leonardorossi.librarify.infra.book.mapper.BookMapper;
import com.leonardorossi.librarify.infra.book.persistence.BookEntity;
import com.leonardorossi.librarify.infra.book.persistence.BookJpaRepository;

/**
 * Adaptador que implementa o repositório de livros para conectar a camada de domínio com a
 * infraestrutura.
 * Este adaptador converte entre entidades de domínio e entidades persistidas.
 */
public class BookRepositoryAdapter implements BookRepository {
  
  private final BookJpaRepository repository;
  private final BookMapper mapper;
  
  public BookRepositoryAdapter(BookJpaRepository bookJpaRepository, BookMapper bookMapper) {
    this.repository = bookJpaRepository;
    this.mapper = bookMapper;
  }
  
  @Override
  public Book save(Book book) {
    BookEntity entity = mapper.toEntity(book);
    return mapper.toDomain(repository.save(entity));
  }
  
  @Override
  public boolean existsByIsbn(String isbn) {
    return repository.existsByIsbn(isbn);
  }
}
