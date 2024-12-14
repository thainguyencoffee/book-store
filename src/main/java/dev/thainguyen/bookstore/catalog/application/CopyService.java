package dev.thainguyen.bookstore.catalog.application;

import dev.thainguyen.bookstore.catalog.application.dto.CopyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CopyService {

  Long registerBookCopy(Long bookId, String barCode);

  Page<CopyDTO> findAll(Pageable pageable);

  CopyDTO findById(Long id);
}
