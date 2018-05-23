import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Rent} from "../model/rent";
import "rxjs/add/operator/catch";

@Injectable()
export class RentService {
  readonly controller = '/rent';

  constructor(private httpClient: HttpClient) {
  }

  save(rentJson: string): Observable<Rent> {
    return this.httpClient.post<Rent>(this.controller + '/save', rentJson).catch(err => Observable.throw(err));
  }

  submitRent(rentId: number): Observable<Rent> {
    return this.httpClient.post<Rent>(this.controller + '/submit-rent/' + rentId,null).catch(err => Observable.throw(err));
  }

  getAllByDate(date: string): Observable<Rent[]> {
    return this.httpClient.get<Rent>(this.controller + '/get-all-by-date/' + date).catch(err => Observable.throw(err));
  }

  getAllByUserId(userId: string): Observable<Rent[]> {
    return this.httpClient.get<Rent>(this.controller + '/get-all-by-user-id/' + userId).catch(err => Observable.throw(err));
  }

}
