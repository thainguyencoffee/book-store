package dev.thainguyen.bookstore.catalog.application.impl;

import dev.thainguyen.bookstore.catalog.application.CopyService;
import dev.thainguyen.bookstore.catalog.application.dto.CopyDTO;
import dev.thainguyen.bookstore.catalog.domain.Copy;
import dev.thainguyen.bookstore.catalog.domain.CopyRepository;
import dev.thainguyen.bookstore.util.ResourceNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CopyServiceImpl implements CopyService {

  private final CopyRepository copyRepository;

  @Override
  public Long registerBookCopy(Long bookId, String barCode) {
    Copy copy = new Copy(new Copy.BookIdentifier(bookId), new Copy.BarCode(barCode));
    copyRepository.save(copy);
    return copy.getId();
  }

  @Override
  public Page<CopyDTO> findAll(Pageable pageable) {
    return copyRepository.findAll(pageable).map(CopyDTO::from);
  }

  @Override
  public CopyDTO findById(Long id) {
    return copyRepository.findById(id)
        .map(CopyDTO::from)
        .orElseThrow(() -> new ResourceNotFound("Copy not found with id: " + id));
  }

}
