import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BookSearchResult} from './book-search-result';

@Injectable({
  providedIn: 'root'
})
export class IsbnSearchService {

  http = inject(HttpClient);
  resourcePath = 'https://openlibrary.org';

  searchByIsbn(isbn: string) {
    return this.http.get<BookSearchResult>(`${this.resourcePath}/isbn/${isbn}.json`)
  }

}
