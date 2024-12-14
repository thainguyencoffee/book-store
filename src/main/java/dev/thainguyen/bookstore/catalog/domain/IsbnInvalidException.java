package dev.thainguyen.bookstore.catalog.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IsbnInvalidException extends RuntimeException{

  public IsbnInvalidException() {
    super("ISBN invalid, please try again");
  }

  public IsbnInvalidException(final String message) {
    super(message);
  }
}
