package dev.thainguyen.bookstore.catalog.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CopyRequestDTO(
  @NotNull(message = "Book id is required")
  Long bookId,
  @NotBlank(message = "Bar code is required")
  String barCode
) {
}
