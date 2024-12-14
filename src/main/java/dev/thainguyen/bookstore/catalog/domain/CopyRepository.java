package dev.thainguyen.bookstore.catalog.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;

public interface CopyRepository extends ListCrudRepository<Copy, Long> {

  Page<Copy> findAll(Pageable pageable);

}
