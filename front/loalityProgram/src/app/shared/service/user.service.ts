import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {User} from "../model/user";
import "rxjs/add/operator/catch";
import 'rxjs/add/observable/throw';
import {url} from "../config/url";
import {PageableWrapper} from "../model/pageable-wrapper";
import {BonusDay} from "../model/bonus-day";
import {Bonus} from "../model/bonus";

@Injectable()
export class UserService {
  readonly controller = '/user';

  constructor(private httpClient: HttpClient) {
  }

  save(userForm: HTMLFormElement, user: User): Observable<User> {
    let form: FormData = new FormData(userForm);
    form.append('userJson', JSON.stringify(user));
    console.log(form.get('userJson'));
    console.log(form.get('img'));
    return this.httpClient.post<User>(this.controller + '/create-user', form).catch(err => Observable.throw(err));
  }

  update(form: HTMLFormElement,user :User){
    let obj :FormData= new FormData(form);
    obj.append('userJson',JSON.stringify(user))
    return this.httpClient.post<User>(this.controller + '/update', obj).catch(err => Observable.throw(err));
  }

  findUserFormRent(): Observable<User[]> {
    return this.httpClient.get<User[]>(this.controller + '/form-find-user').catch(err => Observable.throw(err));
  }

  findAllPageableAvailable(page: number, count: number, nameFilter: string, userModFilter: string, criterionFilter: string): Observable<PageableWrapper> {
    console.log(this.controller + '/find-all-pageable-active/' + page + '/' + count + '/' + encodeURI(nameFilter) + '/' + userModFilter + '/' + criterionFilter);
    return this.httpClient.get<PageableWrapper>(this.controller + '/find-all-pageable-active/' + page + '/' + count + '/' + encodeURI(nameFilter) + '/' + userModFilter + '/' + criterionFilter).catch(err => Observable.throw(err));
  }

  findOne(id: number): Observable<User> {
    return this.httpClient.get<User>(this.controller + '/find-one/' + id).catch(err => Observable.throw(err));
  }

  delete(id: number): Observable<User>{
    return this.httpClient.post<User>(this.controller + '/delete/' + id,null).catch(err => Observable.throw(err));
  }








  findAllDays(): Observable<BonusDay[]>{
    return this.httpClient.get<BonusDay[]>('/bonus-day/find-all').catch(err => Observable.throw(err));
  }
  saveBonusDay(bonusDay:BonusDay): Observable<BonusDay>{
    let form = new FormData();
    form.append('name',bonusDay.name);
    form.append('date',bonusDay.date);
    form.append('bonusesToAdd',bonusDay.bonusesToAdd+'');
    return this.httpClient.post<BonusDay>('/bonus-day/save',form).catch(err => Observable.throw(err));
  }
  deleteBonusDay(bonusDayId:number): Observable<BonusDay>{
    return this.httpClient.post<BonusDay>('/bonus-day/delete/'+bonusDayId,null).catch(err => Observable.throw(err));
  }





}
