package dev.thainguyen.bookstore.catalog.web;

import dev.thainguyen.bookstore.catalog.application.BookService;
import dev.thainguyen.bookstore.catalog.application.dto.BookDTO;
import dev.thainguyen.bookstore.catalog.web.dto.BookRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/books", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;

  @GetMapping
  public ResponseEntity<Page<BookDTO>> getAllBooks(Pageable pageable) {
    return ResponseEntity.ok(bookService.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<BookDTO> getBook(@PathVariable Long id) {
    return ResponseEntity.ok(bookService.findById(id));
  }

  @PostMapping
  public ResponseEntity<Long> addBook(@Valid @RequestBody BookRequestDTO bookRequest) {
    Long bookId = bookService.addBookToCatalog(bookRequest.isbn());
    return ResponseEntity.created(URI.create("/api/books/" + bookId)).body(bookId);
  }

}
