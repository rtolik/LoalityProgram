import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {User} from "../model/user";
import "rxjs/add/operator/catch";
import 'rxjs/add/observable/throw';
import {url} from "../config/url";

@Injectable()
export class UserService {
  readonly controller = url+'/user';

  constructor(private httpClient: HttpClient) {
  }

  save(userForm: HTMLFormElement, userJson: string): Observable<User> {
    let form: FormData = new FormData();
    form.append('user', userJson);
    return this.httpClient.post<User>(this.controller + '/save', form).catch(err => Observable.throw(err));
  }
  findUserFormRent(): Observable<User[]>{
    return this.httpClient.get<User[]>(this.controller + '/form-find-user').catch(err => Observable.throw(err));
  }



}
