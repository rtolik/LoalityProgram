import { Injectable } from '@angular/core';
import "rxjs/add/operator/catch";
import 'rxjs/add/observable/throw';
import {HttpClient} from "@angular/common/http";
import {BonusDay} from "../model/bonus-day";
import {Observable} from "rxjs/Observable";

@Injectable()
export class BonusDayService {
  constructor(private httpClient: HttpClient) {
  }
  findAllDays(): Observable<BonusDay[]>{
    return this.httpClient.get<BonusDay[]>('/celebration-date/find-all').catch(err => Observable.throw(err));
  }
  saveBonusDay(bonusDay:BonusDay): Observable<BonusDay>{
    let form = new FormData();
    form.append('name',bonusDay.name);
    form.append('date',bonusDay.date);
    form.append('bonuses',bonusDay.bonusesToAdd+'');
    return this.httpClient.post<BonusDay>('/celebration-date/add',form).catch(err => Observable.throw(err));
  }
  deleteBonusDay(bonusDayId:number): Observable<BonusDay>{
    return this.httpClient.post<BonusDay>('/celebration-date/delete/'+bonusDayId,null).catch(err => Observable.throw(err));
  }


}
