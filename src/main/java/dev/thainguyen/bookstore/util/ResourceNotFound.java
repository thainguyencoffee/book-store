package dev.thainguyen.bookstore.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException{

  public ResourceNotFound() {
  }

  public ResourceNotFound(final String message) {
    super(message);
  }

}
