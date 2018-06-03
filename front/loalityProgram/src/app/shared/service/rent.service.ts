import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Rent} from "../model/rent";
import "rxjs/add/operator/catch";
import 'rxjs/add/observable/throw';
import {Statistic} from "../model/statistic";

@Injectable()
export class RentService {
  readonly controller = '/rent';

  constructor(private httpClient: HttpClient) {
  }


  delete(id:number):Observable<Rent>{
    return this.httpClient.post<Rent>(this.controller + '/delete/'+id,null ).catch(err => Observable.throw(err));
  }

  getStatistic(start:string,end:string):Observable<Statistic> {
    return this.httpClient.get<Statistic>('/statistics/get/'+start+'/'+end).catch(err => Observable.throw(err));
  }

  save(rent: Rent,userId:number): Observable<Rent> {
    let form = new FormData();
    form.append('date',rent.date);
    form.append('timeOfStart',rent.timeOfStart);
    form.append('duration',rent.duration+'');
    form.append('comment',rent.comment);

    return this.httpClient.post<Rent>(this.controller + '/save/'+userId,form ).catch(err => Observable.throw(err));
  }

  submitRent(rentId: number,price: number,bonus:number): Observable<Rent> {
    return this.httpClient.post<Rent>(this.controller + '/submit/' + rentId+'/'+price+'/'+bonus,null).catch(err => Observable.throw(err));
  }

  updateRent(rent: Rent){
    let formData = new FormData();
    formData.append('comment', rent.comment);
    return this.httpClient.post<Rent>(this.controller + '/update/' + rent.id+'/'+rent.date+'/'+rent.timeOfStart+'/'+rent.duration,formData).catch(err => Observable.throw(err));
  }
  leave(id:number): Observable<Rent>{
    return this.httpClient.post<Rent>(this.controller+'/leave-rent/'+id,null).catch(err => Observable.throw(err));
  }

  getAllByDate(date: string): Observable<Rent[]> {
    return this.httpClient.get<Rent>(this.controller + '/find-all-by-date/',{params: new HttpParams().set('date', date)}).catch(err => Observable.throw(err));
  }
  findOne(id: number) : Observable<Rent>{
    return this.httpClient.get<Rent>(this.controller+'/find-one/'+id).catch(err => Observable.throw(err));
  }

  getAllByUserId(userId: number): Observable<Rent[]> {
    return this.httpClient.get<Rent>(this.controller + '/find-all-by-user-id/' + userId).catch(err => Observable.throw(err));
  }

}
