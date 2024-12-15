package dev.thainguyen.bookstore.catalog.application.dto;

import dev.thainguyen.bookstore.catalog.domain.Book;

import java.util.List;

public record BookDTO(
  Long id,
  String title,
  String isbn,
  List<String> publishers
) {
  public static BookDTO from(Book book) {
    return new BookDTO(
      book.getId(),
      book.getTitle(),
      book.getIsbn().isbn(),
      book.getPublishers());
  }
}
