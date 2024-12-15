import {ApplicationConfig, importProvidersFrom, provideZoneChangeDetection} from '@angular/core';
import {ExtraOptions, RouterModule} from '@angular/router';

import { routes } from './app.routes';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {provideHttpClient} from '@angular/common/http';

const routeConfig: ExtraOptions = {
  onSameUrlNavigation: 'reload',
  scrollPositionRestoration: 'enabled'
};

export const appConfig: ApplicationConfig = {
  providers: [
    importProvidersFrom(RouterModule.forRoot(routes, routeConfig)),
    BrowserAnimationsModule,
    provideHttpClient(),
    provideZoneChangeDetection({ eventCoalescing: true })
  ]
};
