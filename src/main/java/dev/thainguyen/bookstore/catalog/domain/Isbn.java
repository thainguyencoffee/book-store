package dev.thainguyen.bookstore.catalog.domain;

import org.apache.commons.validator.routines.ISBNValidator;

public record Isbn(String isbn) {

  private static final ISBNValidator VALIDATOR = new ISBNValidator();

  public Isbn {
    if (!VALIDATOR.isValidISBN10(isbn) && !VALIDATOR.isValidISBN13(isbn))
      throw new IsbnInvalidException();
  }

}
