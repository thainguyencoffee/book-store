package dev.thainguyen.bookstore.catalog.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record BookRequestDTO(
  @NotBlank(message = "Isbn is required")
  @Size(min = 10, max = 13, message = "Isbn must be between 10 and 13 characters")
  String isbn
) {
}
