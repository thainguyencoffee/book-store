package dev.thainguyen.bookstore.catalog.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;

public interface BookRepository extends ListCrudRepository<Book, Long> {

  Page<Book> findAll(Pageable pageable);

}
