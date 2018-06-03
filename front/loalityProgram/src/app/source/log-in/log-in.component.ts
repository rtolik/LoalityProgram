import {Component, OnInit} from '@angular/core';
import {UserService} from "../../shared/service/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css'],
})
export class LogInComponent implements OnInit {

  constructor(private _user: UserService, private _router: Router) {
  }


  logIn(log: string, pass: string, check: HTMLInputElement) {
    this._user.logIn(log, pass).subscribe(next => {
      this._user.logData(log, pass, next, check.checked);
      if (next) {
        // console.log('navigating');
        this._router.navigateByUrl('');
      }
    }, error => {
      console.log(error);
    })
  }

  ngOnInit() {
  }

}
