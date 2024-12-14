import { Routes } from '@angular/router';
import {BookComponent} from './book/book.component';

export const routes: Routes = [
  {
    path: '',
    component: BookComponent,
    title: $localize`:@@book.list.headLine: Welcome to home page`
  }
];
