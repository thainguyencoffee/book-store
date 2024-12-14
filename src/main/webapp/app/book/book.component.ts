import {Component, inject, OnDestroy, OnInit} from '@angular/core';
import {BookDTO} from './book.model';
import {ErrorHandler} from '../common/error-handler.injectable';
import {Subscription} from 'rxjs';
import {BookService} from './book.service';
import {PaginationUtils} from '../common/page-wrapper';
import {NavigationEnd, Router, RouterLink} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {IsbnSearchService} from './isbn-search.service';
import {BookSearchResult} from './book-search-result';
import {NgForOf, NgIf} from '@angular/common';
import {BookRequestDto} from './book-request.dto';

@Component({
  selector: 'app-home',
  imports: [
    RouterLink,
    ReactiveFormsModule,
    FormsModule,
    NgIf,
    NgForOf,
  ],
  templateUrl: './book.component.html',
  styleUrl: './book.component.scss'
})
export class BookComponent implements OnInit, OnDestroy{

  router = inject(Router);
  bookService = inject(BookService);
  isbnSearchService = inject(IsbnSearchService);
  errorHandler = inject(ErrorHandler);

  books?: BookDTO[];
  paginationUtils?: PaginationUtils;
  navigationSubscription?: Subscription;

  toggleCreateBook: boolean = false;

  isbnSearch: string = '';
  loading: boolean = false;
  bookSearchResult?: BookSearchResult;

  getMessage(key: string, details?: any) {
    const messages: Record<string, string> = {
      confirmDelete: $localize`:@@delete.confirm:Do you really want to delete this element? This cannot be undone.`,
      deleted: $localize`:@@book.delete.success:Book was removed successfully.`,
      notFound: $localize`:@@book.search.notFound:Book search not found.`,
      confirmAdd: $localize`:@@add.confirm:Do you really want to add this element?.`,
      added: $localize`:@@book.add.success:Book with isbn: ${details?.isbn} was added successfully. BookID: ${details?.bookId}.`
    };
    return messages[key];
  }

  handleSubmit() {
    if (confirm(this.getMessage('confirmAdd')) && this.bookSearchResult && this.bookSearchResult.isbn_13[0]) {
      window.scrollTo(0, 0);
      const isbn = this.bookSearchResult.isbn_13[0];

      const data = new BookRequestDto({
        isbn: isbn
      });

      this.bookService.addBookToCatalog(data)
        .subscribe({
          next: bookId => this.router.navigate(['/'], {
            state: {
              msgSuccess: this.getMessage('added', {isbn, bookId})
            }
          }),
          error: error => this.errorHandler.handleServerError(error.error)
        })
    }
  }

  ngOnInit(): void {
    this.loadData(0);
    this.navigationSubscription = this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.loadData(0);
      }
    })
  }

  loadData(pageNumber: number) {
    this.bookService.getAllBooks(pageNumber)
      .subscribe({
        next: (data) => {
          this.paginationUtils = new PaginationUtils(data.page);
          this.books = data.content;
        },
        error: (error) => this.errorHandler.handleServerError(error.error)
      });
  }

  ngOnDestroy(): void {
    this.navigationSubscription?.unsubscribe();
  }

  onPageChange(pageNumber: number): void {
    if (pageNumber >= 0 && pageNumber < this.paginationUtils!.totalPages) {
      this.loadData(pageNumber);
    }
  }

  getPageRange(): number[] {
    return this.paginationUtils?.getPageRange() || [];
  }

  confirmDelete(bookId: number) {
    if (confirm(this.getMessage('confirmDelete'))) {
      this.bookService.deleteBook(bookId)
        .subscribe({
          next: () => this.router.navigate(['/'], {
            state: {
              msgInfo: this.getMessage('deleted')
            }
          }),
          error: error => this.errorHandler.handleServerError(error.error)
        })
    }
  }


  handleSearchBook() {
    if (this.isbnSearch.trim()) {
      this.bookSearchResult = undefined;
      this.loading = true;

      this.isbnSearchService.searchByIsbn(this.isbnSearch).subscribe({
        next: bookResult => {
          this.bookSearchResult = bookResult;
          this.loading = false;
          this.isbnSearch = '';
        },
        error: (error) => {
          if (error.status === 404) {
            confirm(this.getMessage('notFound', { isbn: this.isbnSearch } ))
          }
          this.loading = false;
          this.isbnSearch = '';
        }
      })

    }
  }

}
