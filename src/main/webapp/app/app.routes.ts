import { Routes } from '@angular/router';
import {BookComponent} from './book/book.component';
import {ErrorComponent} from './error/error.component';

export const routes: Routes = [
  {
    path: '',
    component: BookComponent,
    title: `Welcome to home page`
  },
  {
    path: 'error',
    component: ErrorComponent,
    title: `Error`
  },
  {
    path: '**',
    component: ErrorComponent,
    title: `Page Not Found`
  }
];
