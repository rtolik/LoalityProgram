import { Component, OnInit } from '@angular/core';
import {User} from "../../../shared/model/user";
import {UserService} from "../../../shared/service/user.service";
import {PageableWrapper} from "../../../shared/model/pageable-wrapper";

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



  currentPage:number = 1;
  constructor(private _userService: UserService) {
    this._userService.findAllPageableAvailable(this.currentPage-1,10,'empty',this.userModFilter,this.criterionFilter).subscribe(next=>{
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
    if (str == '') {
      str = 'empty'
    }
    this._userService.findAllPageableAvailable(page-1,10,str,this.userModFilter,this.criterionFilter).subscribe(next=>{
      console.log('loading');
      this.numOfPages=next.numOfPages;
      this.users=next.users;
    })
  }

  ngOnInit() {
  }

}
