package com.leonardorossi.librarify.presentation.recommendation;

import com.leonardorossi.librarify.application.reccomendation.usecase.RecommendBookByCategoryUseCase;
import com.leonardorossi.librarify.domain.recommendation.Recommendation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável por gerenciar as operações relacionadas a recomendações.
 */
@RestController
@RequestMapping("/recommendation")
public class RecommendationController {
  
  private final RecommendBookByCategoryUseCase recommendBookByCategoryUseCase;
  
  public RecommendationController(
      RecommendBookByCategoryUseCase recommendBookByCategoryUseCase
  ) {
    this.recommendBookByCategoryUseCase = recommendBookByCategoryUseCase;
  }
  
  @GetMapping("/{idUser}")
  public ResponseEntity<Recommendation> recommendByCategory(
      @PathVariable Long idUser
  ) {
    return ResponseEntity.ok(recommendBookByCategoryUseCase.execute(idUser));
  }
}
