package com.leonardorossi.librarify.application.reccomendation.gateways;

import com.leonardorossi.librarify.domain.recommendation.Recommendation;
import java.util.List;

/**
 * Interface que define o contrato do repositório de recomendações.
 * Implementações desta interface devem lidar com operações relacionadas a recomendações de livros.
 */
public interface RecommendationRepository {
  Recommendation recommendByCategory(List<Long> borrowedBooks, String category);
}
