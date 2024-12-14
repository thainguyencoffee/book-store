import {ApplicationConfig, importProvidersFrom, provideZoneChangeDetection} from '@angular/core';
import {ExtraOptions, RouterModule, TitleStrategy} from '@angular/router';

import { routes } from './app.routes';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {provideHttpClient} from '@angular/common/http';
import {CustomTitleStrategy} from './common/title-strategy.injectable';

const routeConfig: ExtraOptions = {
  onSameUrlNavigation: 'reload',
  scrollPositionRestoration: 'enabled'
};

export const appConfig: ApplicationConfig = {
  providers: [
    importProvidersFrom(RouterModule.forRoot(routes, routeConfig)),
    BrowserAnimationsModule,
    provideHttpClient(),
    provideZoneChangeDetection({ eventCoalescing: true }),
    {
      provide: TitleStrategy,
      useClass: CustomTitleStrategy
    }
  ]
};
