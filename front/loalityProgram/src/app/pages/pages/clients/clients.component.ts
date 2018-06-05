import { Component, OnInit } from '@angular/core';
import {User} from "../../../shared/model/user";
import {UserService} from "../../../shared/service/user.service";
import {PageableWrapper} from "../../../shared/model/pageable-wrapper";
import {phoneMask} from "../../../shared/config/config";

@Component({
  selector: 'app-clients',
  templateUrl: './clients.component.html',
  styleUrls: ['./clients.component.css'],
  providers: [UserService]
})
export class ClientsComponent implements OnInit {
  numOfPages:number;
  users:User[]=[];
  userModFilter: string='all';
  criterionFilter: string='date';
  userName = '';
  userPhone='';
  phoneMask= phoneMask;



  currentPage:number = 1;
  constructor(private _userService: UserService) {
    this._userService.findAllPageableAvailable(this.currentPage-1,10,'empty','empty',this.userModFilter,this.criterionFilter).subscribe(next=>{
      console.log(next);
      this.numOfPages=next.numOfPages;
      console.log(next.users);
      this.users=next.users;
    })
  }

  userModFilterFunc(str: string) {
    this.userModFilter = str;
    this.loadPage(1)
  }

  criterionFilterFunc(str: string) {
    this.criterionFilter = str;
    this.loadPage(1)
  }

  loadPage(page: number) {
    let str = this.userName;
    let str2=this.userPhone;
    if (str == '') {
      str = 'empty';
    }
    if(str2==''){
      str2='empty';
    }
    this._userService.findAllPageableAvailable(page-1,10,str,str2,this.userModFilter,this.criterionFilter).subscribe(next=>{
      console.log('loading');
      console.log(next.users);
      this.numOfPages=next.numOfPages;
      this.users=next.users;
      this.currentPage=page;
    })
  }

  ngOnInit() {
  }

}
