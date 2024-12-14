package dev.thainguyen.bookstore.catalog.application.dto;

import dev.thainguyen.bookstore.catalog.domain.Book;

public record BookDTO(
  Long id,
  String title,
  String isbn
) {
  public static BookDTO from(Book book) {
    return new BookDTO(book.getId(), book.getTitle(), book.getIsbn().isbn());
  }
}
