package com.leonardorossi.librarify.domain.recommendation;

import com.leonardorossi.librarify.domain.book.entity.Book;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidade de domínio que representa uma recomendação.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Recommendation {
  private List<Book> bookList;
}
