import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';
import {UserService} from "../service/user.service";
import {isNullOrUndefined} from "util";


@Injectable()
export class LogInCanActive implements CanActivate {
  constructor(private _router: Router, private _user: UserService, @Inject(PLATFORM_ID) private platformId: Object) {

  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    let bool :boolean;
    bool = this._user.isLogged;
    if (!isNullOrUndefined(localStorage.getItem('is_auth'))) {
      bool = true;
    }
    if (!bool) {
      this._router.navigateByUrl('/log-in');
    }
    return bool;
  }
}
