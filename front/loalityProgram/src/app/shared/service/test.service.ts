import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {User} from "../model/user";
import "rxjs/add/operator/catch";
import 'rxjs/add/observable/throw';
import {url} from "../config/url";

@Injectable()
export class TestService {
  readonly controller = url+'/Hello';

  constructor(private httpClient: HttpClient) {
  }

  test(): Observable<User> {
    return this.httpClient.get<User>(this.controller + '/world').catch(err => Observable.throw(err));
  }



}
