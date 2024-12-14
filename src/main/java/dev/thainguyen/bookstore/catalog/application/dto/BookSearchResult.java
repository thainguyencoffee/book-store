package dev.thainguyen.bookstore.catalog.application.dto;

import java.util.List;

public record BookSearchResult(
  List<String> publishers,
  String title,
  List<String> isbn_13,
  int revision
) {
}
