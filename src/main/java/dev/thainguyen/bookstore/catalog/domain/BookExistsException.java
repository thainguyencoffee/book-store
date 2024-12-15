package dev.thainguyen.bookstore.catalog.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookExistsException extends RuntimeException{

  public BookExistsException(final String message) {
    super(message);
  }

}
