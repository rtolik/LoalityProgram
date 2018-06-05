import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';
import {UserService} from "../service/user.service";
import {isNullOrUndefined} from "util";
import {xor} from "../config/config";


@Injectable()
export class LogInCanActive implements CanActivate {
  constructor(private _router: Router, private _user: UserService, @Inject(PLATFORM_ID) private platformId: Object) {

  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    let bool :boolean;
    bool = this._user.isLogged;
    this._user.isLogged$.subscribe(next=>{
        bool=next;
    });
    if (!isNullOrUndefined(localStorage.getItem('xored'))) {
      if (localStorage.getItem('xored') == xor(localStorage.getItem('login'), localStorage.getItem('password')))
        bool = true;
    }
    if (!bool) {
      this._router.navigateByUrl('/log-in');
    }
    return bool;
  }
}
