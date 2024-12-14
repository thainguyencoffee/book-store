package dev.thainguyen.bookstore.catalog.application;

import dev.thainguyen.bookstore.catalog.application.dto.BookSearchResult;
import dev.thainguyen.bookstore.catalog.domain.Isbn;

public interface IsbnSearchService {

  BookSearchResult search(Isbn isbn);

}
