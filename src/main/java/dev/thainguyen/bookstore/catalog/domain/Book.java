package dev.thainguyen.bookstore.catalog.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.util.Assert;

@Table("book")
@Getter
@ToString
public class Book {

  @Id
  private Long id;
  private String title;
  @Embedded.Nullable
  private Isbn isbn;

  public Book(String title, Isbn isbn) {
    Assert.hasText(title, "Title cannot be empty");
    Assert.notNull(isbn, "Isbn cannot be null");

    this.title = title;
    this.isbn = isbn;
  }

}