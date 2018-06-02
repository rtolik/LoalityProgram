import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Rent} from "../model/rent";
import "rxjs/add/operator/catch";
import 'rxjs/add/observable/throw';

@Injectable()
export class RentService {
  readonly controller = '/rent';

  constructor(private httpClient: HttpClient) {
  }



  save(rent: Rent,userId:number): Observable<Rent> {
    let form = new FormData();
    form.append('date',rent.date);
    form.append('timeOfStart',rent.timeOfStart);
    form.append('duration',rent.duration+'');
    form.append('comment',rent.comment);

    return this.httpClient.post<Rent>(this.controller + '/save/'+userId,form ).catch(err => Observable.throw(err));
  }

  submitRent(rentId: number): Observable<Rent> {
    return this.httpClient.post<Rent>(this.controller + '/submit-rent/' + rentId,null).catch(err => Observable.throw(err));
  }

  getAllByDate(date: string): Observable<Rent[]> {
    return this.httpClient.get<Rent>(this.controller + '/find-all-by-date/',{params: new HttpParams().set('date', date)}).catch(err => Observable.throw(err));
  }

  getAllByUserId(userId: number): Observable<Rent[]> {
    return this.httpClient.get<Rent>(this.controller + '/find-all-by-user-id/' + userId).catch(err => Observable.throw(err));
  }

}
