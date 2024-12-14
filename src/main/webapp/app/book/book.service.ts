import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {PageWrapper} from '../common/page-wrapper';
import {BookRequestDto} from './book-request.dto';
import {BookDTO} from './book.model';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  http = inject(HttpClient);
  resourcePath = environment.apiPath + '/api/books';

  getAllBooks(pageNumber: number = 0, pageSize: number = 10) {
    const url = `${this.resourcePath}?page=${pageNumber}&size=${pageSize}`;
    return this.http.get<PageWrapper<BookDTO>>(url);
  }

  deleteBook(bookId: number) {
    return this.http.delete(`${this.resourcePath}/${bookId}`);
  }

  addBookToCatalog(data: BookRequestDto) {
    return this.http.post<number>(this.resourcePath, data);
  }
}
