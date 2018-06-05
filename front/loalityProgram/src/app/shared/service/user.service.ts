import {Injectable} from '@angular/core';
import {HttpClient, } from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {User} from "../model/user";
import "rxjs/add/operator/catch";
import 'rxjs/add/observable/throw';
import {PageableWrapper} from "../model/pageable-wrapper";
import {BonusDay} from "../model/bonus-day";
import {Subject} from "rxjs/Subject";
import {Auth} from "../model/auth";


@Injectable()
export class UserService {
  readonly controller = '/user';

  isLogged: boolean = false;
  _isLogged = new Subject<boolean>();
  isLogged$ = this._isLogged.asObservable();


  login: string = '';
  _login = new Subject<string>();
  login$ = this._login.asObservable();


  password: string = '';
  _password = new Subject<string>();
  password$ = this._password.asObservable();

  constructor(private httpClient: HttpClient) {
  }

  save(userForm: HTMLFormElement, user: User): Observable<User> {
    let form: FormData = new FormData(userForm);
    form.append('userJson', JSON.stringify(user));
    return this.httpClient.post<User>(this.controller + '/create-user', form).catch(err => Observable.throw(err));
  }

  update(form: HTMLFormElement,user :User){
    let obj :FormData= new FormData(form);
    obj.append('userJson',JSON.stringify(user));
    return this.httpClient.post<User>(this.controller + '/update', obj).catch(err => Observable.throw(err));
  }

  findUserFormRent(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.controller + '/form-find-user').catch(err => Observable.throw(err));
  }

  findAllPageableAvailable(page: number, count: number, nameFilter: string,phoneFilter:string, userModFilter: string, criterionFilter: string): Observable<PageableWrapper> {
    console.log(this.controller + '/find-all-pageable-active/' + page + '/' + count + '/' + encodeURI(nameFilter)+'/'+phoneFilter+ '/' + userModFilter + '/' + criterionFilter);
    return this.httpClient.get<PageableWrapper>(this.controller + '/find-all-pageable-active/' + page + '/' + count + '/' + encodeURI(nameFilter)+'/'+phoneFilter+ '/' + userModFilter + '/' + criterionFilter).catch(err => Observable.throw(err));
  }

  findOne(id: number): Observable<User> {
    return this.httpClient.get<User>(this.controller + '/find-one/' + id).catch(err => Observable.throw(err));
  }

  delete(id: number): Observable<User>{
    return this.httpClient.post<User>(this.controller + '/delete/' + id,null).catch(err => Observable.throw(err));
  }
  revive(id: number): Observable<User>{
    return this.httpClient.post<User>(this.controller + '/recover/' + id,null).catch(err => Observable.throw(err));
  }










  logIn(login: string, password: string): Observable<Auth> {
    let form = new FormData();
    form.append('login',login);
    form.append('password',password);
    return this.httpClient.post<Auth>(this.controller + '/log-in', form).catch(err => Observable.throw(err));
  }




  logData(log:string,pass:string,auth:Auth,check:boolean){
    if(auth.authorised){
      this.isLogged=true;
      this._isLogged.next(this.isLogged);
      this.login=log;
      this._login.next(this.login);
      this.password=pass;
      this._password.next(this.password);
      if(check){
        localStorage.setItem('xored',auth.xored);
        localStorage.setItem('login', this.login);
        localStorage.setItem('password', this.password);
      }
    }
  }



}
