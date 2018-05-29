import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {User} from "../model/user";
import "rxjs/add/operator/catch";
import 'rxjs/add/observable/throw';
import {url} from "../config/url";
import {PageableWrapper} from "../model/pageable-wrapper";

@Injectable()
export class UserService {
  readonly controller = url+'/user';

  constructor(private httpClient: HttpClient) {
  }

  save(userForm: HTMLFormElement, user: User): Observable<User> {
    let form: FormData = new FormData(userForm);
    form.append('userJson', JSON.stringify(user));
    return this.httpClient.post<User>(this.controller + '/create-user', form).catch(err => Observable.throw(err));
  }
  findUserFormRent(): Observable<User[]>{
    return this.httpClient.get<User[]>(this.controller + '/form-find-user').catch(err => Observable.throw(err));
  }
  findAllPageableAvailable(page:number,count:number,nameFilter:string,userModFilter:string,criterionFilter:string): Observable<PageableWrapper> {
    return this.httpClient.get<PageableWrapper>(this.controller+'/find-all-pageable-active/'+page+'/'+count+'/'+encodeURI(nameFilter)+'/'+userModFilter+'/'+criterionFilter).catch(err => Observable.throw(err));
  }



}
