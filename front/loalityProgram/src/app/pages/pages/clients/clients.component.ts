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
  criterionFilter: string='alp';

  currentPage:number = 1;
  constructor(private _userService: UserService) {
    this._userService.findAllPageableAvailable(this.currentPage-1,10,'empty',this.userModFilter,this.criterionFilter).subscribe(next=>{
      console.log(next);
      this.numOfPages=next.numOfPages;
      console.log(next.users);
      this.users=next.users;
    })
  }

  loadPage(page:number, str:string){
    if (str==''){
      str='empty';
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
