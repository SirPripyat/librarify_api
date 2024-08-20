package com.leonardorossi.librarify.config.recommendation;

import com.leonardorossi.librarify.application.loan.usecase.FindLastBooksCheckedOutByUserUseCase;
import com.leonardorossi.librarify.application.reccomendation.gateways.RecommendationRepository;
import com.leonardorossi.librarify.application.reccomendation.usecase.RecommendBookByCategoryUseCase;
import com.leonardorossi.librarify.infra.recommendation.gateways.RecommendationRepositoryAdapter;
import com.leonardorossi.librarify.infra.recommendation.mapper.RecommendationEntityMapper;
import com.leonardorossi.librarify.infra.recommendation.persistence.RecommendationJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração para recomendações.
 */
@Configuration
public class RecommendationConfig {
  
  @Bean
  RecommendBookByCategoryUseCase recommendBookByCategoryUseCase(
      RecommendationRepository repository,
      FindLastBooksCheckedOutByUserUseCase findLastBooksCheckedOutByUserUseCase
  ) {
    return new RecommendBookByCategoryUseCase(
      repository,
      findLastBooksCheckedOutByUserUseCase
    );
  }
  
  @Bean
  RecommendationRepositoryAdapter recommendationRepositoryAdapter(
      RecommendationJpaRepository repository,
      RecommendationEntityMapper mapper
  ) {
    return new RecommendationRepositoryAdapter(repository, mapper);
  }
}
