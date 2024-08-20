package com.leonardorossi.librarify.infra.loan.persistence.repository;

import com.leonardorossi.librarify.infra.book.persistence.BookEntity;
import com.leonardorossi.librarify.infra.loan.persistence.LoanEntity;
import com.leonardorossi.librarify.infra.user.persistence.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Implementação do repositório de empréstimos que utiliza CriteriaBuilder para construir consultas
 * personalizadas.
 */
@Repository
public class LoanCriteriaBuilderRepositoryImpl implements LoanCriteriaBuilderRepository {
  
  private final EntityManager entityManager;
  
  private static final int MAX_RESULTS = 20;
  
  @Autowired
  public LoanCriteriaBuilderRepositoryImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }
  
  @Override
  public Optional<List<BookEntity>> findLastBooksCheckOutByUser(Long userId) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<BookEntity> query = criteriaBuilder.createQuery(BookEntity.class);
    Root<UserEntity> userEntityRoot = query.from(UserEntity.class);
    
    Join<UserEntity, LoanEntity> loanEntityJoin = userEntityRoot.join("listOfLoans");
    Join<LoanEntity, BookEntity> bookEntityJoin = loanEntityJoin.join("bookEntity");
    
    query.select(bookEntityJoin)
      .where(criteriaBuilder.equal(userEntityRoot.get("id"), userId))
        .orderBy(criteriaBuilder.desc(loanEntityJoin.get("id")));
      
    return Optional.ofNullable(entityManager.createQuery(query)
      .setMaxResults(MAX_RESULTS)
      .getResultList());
  }

}
