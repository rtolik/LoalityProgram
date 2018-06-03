import {Inject, Injectable, PLATFORM_ID} from '@angular/core';
import {HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {isNull} from 'util';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import {isPlatformBrowser} from '@angular/common';
import {url} from "../config/url";

@Injectable()
export class ContentInterceptor implements HttpInterceptor {

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {
  }

  intercept<T>(req: HttpRequest<T>, next: HttpHandler): Observable<HttpEvent<T>> {
    console.log('kurvamat');
    req = req.clone({url: url + req.url});
    if (isPlatformBrowser(this.platformId)) {
      req = req.clone({headers: this.getHeaders(req)});
    }
    return next.handle(req);
  }

  getHeaders(req: HttpRequest<any>): HttpHeaders {
    let headers = new HttpHeaders();
    let temp: HttpRequest<any>;
    if (isNull(req.headers)) {
      temp = req.clone({headers});
    } else {
      temp = req.clone();
    }
    headers = temp.headers;
    headers = headers.append('enctype', 'form-data/multipart');
    headers = headers.append('Accept', 'application/json');
    return headers;
  }

}
