package dev.thainguyen.bookstore.catalog.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.util.Assert;

@Table("copy")
@Getter
@ToString
public class Copy {

  @Id
  private Long id;
  @Embedded.Nullable
  private BarCode barCode;
  @Embedded.Nullable
  private BookIdentifier bookId;
  private boolean available;

  public Copy(BookIdentifier bookId, BarCode barCode) {
    this.barCode = barCode;
    this.bookId = bookId;
    this.available = true;
  }

  public record BarCode(String code){
    public BarCode {
      Assert.hasText(code, "Code must not be empty");
    }
  }

  public record BookIdentifier(Long bookId) {
    public BookIdentifier {
      Assert.notNull(bookId, "Book id must not be null");
    }
  }

}
