package dev.thainguyen.bookstore.catalog.web;

import dev.thainguyen.bookstore.catalog.application.CopyService;
import dev.thainguyen.bookstore.catalog.application.dto.CopyDTO;
import dev.thainguyen.bookstore.catalog.web.dto.CopyRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/copies", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CopyController {

  private final CopyService copyService;

  @GetMapping
  public ResponseEntity<Page<CopyDTO>> getAllCopies(Pageable pageable) {
    return ResponseEntity.ok(copyService.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CopyDTO> getCopy(@PathVariable Long id) {
    return ResponseEntity.ok(copyService.findById(id));
  }

  @PostMapping
  public ResponseEntity<Long> registerCopy(@Valid @RequestBody CopyRequestDTO copyRequestDTO) {
    Long copyId = copyService.registerBookCopy(copyRequestDTO.bookId(), copyRequestDTO.barCode());
    return ResponseEntity.created(URI.create("/api/copies/" + copyId)).body(copyId);
  }

}
