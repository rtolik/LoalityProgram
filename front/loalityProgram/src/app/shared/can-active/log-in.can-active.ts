import {ActivatedRouteSnapshot, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';
import {UserService} from "../service/user.service";
import {isNullOrUndefined} from "util";


@Injectable()
export class LogInCanActive {
  constructor(private _router: Router, private _user: UserService, @Inject(PLATFORM_ID) private platformId: Object) {

  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    let bool=false;
    bool=this._user.isLogged;
    this._user.isLogged$.subscribe(next=>{
        bool=next;
    });
    if(!isNullOrUndefined(localStorage.getItem(this._user.login))){
      bool=true;
    }
    if(bool==false){
      this._router.navigateByUrl('/log-in');
    }
   return bool;
  }
}
