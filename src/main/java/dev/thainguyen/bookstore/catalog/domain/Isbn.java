package dev.thainguyen.bookstore.catalog.domain;

import org.apache.commons.validator.routines.ISBNValidator;

public record Isbn(String isbn) {

  private static final ISBNValidator VALIDATOR = new ISBNValidator();

  public Isbn {
    if (!VALIDATOR.isValid(isbn))
      throw new IsbnInvalidException();
  }

}
