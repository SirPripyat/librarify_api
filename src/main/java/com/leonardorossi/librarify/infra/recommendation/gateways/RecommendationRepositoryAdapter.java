package com.leonardorossi.librarify.infra.recommendation.gateways;

import com.leonardorossi.librarify.application.reccomendation.gateways.RecommendationRepository;
import com.leonardorossi.librarify.domain.recommendation.Recommendation;
import com.leonardorossi.librarify.infra.book.persistence.BookEntity;
import com.leonardorossi.librarify.infra.recommendation.mapper.RecommendationEntityMapper;
import com.leonardorossi.librarify.infra.recommendation.persistence.RecommendationJpaRepository;
import java.util.List;

/**
 * Adaptador que implementa o repositório de recomendações para conectar a camada de domínio com a
 * infraestrutura.
 * Este adaptador converte entre entidades de domínio e entidades persistidas.
 */
public class RecommendationRepositoryAdapter implements RecommendationRepository {
  private final RecommendationJpaRepository repository;
  private final RecommendationEntityMapper mapper;
  
  public RecommendationRepositoryAdapter(
      RecommendationJpaRepository repository,
      RecommendationEntityMapper mapper
  ) {
    this.repository = repository;
    this.mapper = mapper;
  }
  
  @Override
  public Recommendation recommendByCategory(List<Long> borrowedBooks, String category) {
    List<BookEntity> bookEntities = repository
        .findAllByIdIsNotInAndCategory(borrowedBooks, category);
    
    return mapper.toDomain(bookEntities);
  }
}
