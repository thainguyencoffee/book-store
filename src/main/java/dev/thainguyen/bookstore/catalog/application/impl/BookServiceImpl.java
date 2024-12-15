package dev.thainguyen.bookstore.catalog.application.impl;

import dev.thainguyen.bookstore.catalog.application.BookService;
import dev.thainguyen.bookstore.catalog.application.IsbnSearchService;
import dev.thainguyen.bookstore.catalog.application.dto.BookDTO;
import dev.thainguyen.bookstore.catalog.application.dto.BookSearchResult;
import dev.thainguyen.bookstore.catalog.domain.Book;
import dev.thainguyen.bookstore.catalog.domain.BookRepository;
import dev.thainguyen.bookstore.catalog.domain.Isbn;
import dev.thainguyen.bookstore.util.ResourceNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

  private final IsbnSearchService isbnSearchService;
  private final BookRepository bookRepository;

  @Override
  public Long addBookToCatalog(String isbnString) {
    Isbn isbn = new Isbn(isbnString);

    BookSearchResult bookSearchResult = isbnSearchService.search(isbn);
    Book book = new Book(bookSearchResult.title(), isbn);
    bookRepository.save(book);
    return book.getId();
  }

  @Override
  public Page<BookDTO> findAll(Pageable pageable) {
    return bookRepository.findAll(pageable).map(BookDTO::from);
  }

  @Override
  public BookDTO findById(Long id) {
    return bookRepository.findById(id)
        .map(BookDTO::from)
        .orElseThrow(() -> new ResourceNotFound("Book not found with id: " + id));
  }

  @Override
  public void deleteBook(Long id) {
    bookRepository.deleteById(id);
  }

}
