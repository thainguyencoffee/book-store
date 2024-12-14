package dev.thainguyen.bookstore.catalog.application.dto;

import dev.thainguyen.bookstore.catalog.domain.Copy;

public record CopyDTO(
  Long id,
  String code,
  Long bookId,
  boolean available
) {

  public static CopyDTO from(Copy copy) {
    return new CopyDTO(
      copy.getId(),
      copy.getBarCode().code(),
      copy.getBookId().bookId(),
      copy.isAvailable());
  }

}
