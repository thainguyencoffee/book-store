package dev.thainguyen.bookstore.catalog.application.impl;

import dev.thainguyen.bookstore.catalog.application.IsbnSearchService;
import dev.thainguyen.bookstore.catalog.application.dto.BookSearchResult;
import dev.thainguyen.bookstore.catalog.domain.Isbn;
import dev.thainguyen.bookstore.util.ResourceNotFound;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class IsbnSearchServiceImpl implements IsbnSearchService {

  private final RestTemplate restTemplate;

  public IsbnSearchServiceImpl(RestTemplateBuilder builder) {
    this.restTemplate = builder.rootUri("https://openlibrary.org").build();
  }

  @Override
  public BookSearchResult search(Isbn isbn) {
    try {
      return restTemplate.getForObject("/isbn/{isbn}.json", BookSearchResult.class, isbn.isbn());
    } catch (HttpClientErrorException.NotFound e) {
      throw new ResourceNotFound("Book not found for ISBN: " + isbn.isbn());
    }
  }

}
