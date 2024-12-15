import {Component, inject, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {getReasonPhrase} from 'http-status-codes';

@Component({
  selector: 'app-error',
  imports: [],
  templateUrl: './error.component.html',
  styleUrl: './error.component.scss'
})
export class ErrorComponent implements OnInit{

  router = inject(Router);

  status = '404';
  error = getReasonPhrase(this.status);

  getMessage(status: string) {
    const messages: Record<string, string> = {
      '400': `WTF cái này là rác rưởi gì thế? Đừng mong tôi giải quyết chuyện vô lý này`,
      '404': `Bất cứ thứ gì bạn đang tìm kiếm đều không có ở đây`,
    };
    return messages[status];
  }

  ngOnInit() {
    const currentNavigation = this.router.lastSuccessfulNavigation;
    if (currentNavigation?.initialUrl.toString() !== '/error') {
      // show not found
      return;
    }
    const navigationState = currentNavigation.extras.state;
    this.status = navigationState?.['errorStatus'] || '503';
    this.error = navigationState?.['errorMessage'] || getReasonPhrase(this.status);
  }

}
