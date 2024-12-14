import {RouterStateSnapshot, TitleStrategy} from '@angular/router';
import {Injectable} from '@angular/core';
import {$localize} from '@angular/localize/init';
import {Title} from '@angular/platform-browser';

@Injectable()
export class CustomTitleStrategy extends TitleStrategy {

  titleSuffix = $localize`:@@app.title:MyApp`;

  constructor(private readonly title: Title) {
    super();
  }

  updateTitle(routerState: RouterStateSnapshot): void {
    const title = this.buildTitle(routerState);
    if (title !== undefined) {
      this.title.setTitle(title + ' - ' + this.titleSuffix);
    } else {
      this.title.setTitle(this.titleSuffix);
    }
  }

}
