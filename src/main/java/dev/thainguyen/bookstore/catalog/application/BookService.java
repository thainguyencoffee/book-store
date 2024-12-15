package dev.thainguyen.bookstore.catalog.application;

import dev.thainguyen.bookstore.catalog.application.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

  Long addBookToCatalog(String isbnString);

  Page<BookDTO> findAll(Pageable pageable);

  BookDTO findById(Long id);

  void deleteBook(Long id);

}
