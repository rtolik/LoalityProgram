import { Component, OnInit } from '@angular/core';
import {UserService} from "../../shared/service/user.service";

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css'],
  providers: [UserService]
})
export class LogInComponent implements OnInit {

  constructor(private _user: UserService) {
  }


  logIn(log:string,pass:string,check:HTMLInputElement){
    this._user.logIn(log,pass).subscribe(next=>{
      this._user.logData(log,pass,next,check.checked);
    },error => {
        console.log(error);
    })
  }
  ngOnInit() {
  }

}
