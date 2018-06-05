import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../../../../shared/service/user.service";
import {User} from "../../../../shared/model/user";
import {RentService} from "../../../../shared/service/rent.service";
import {Rent} from "../../../../shared/model/rent";
import {isNullOrUndefined} from "util";
import {ImagePipe} from "../../../../shared/pipe/pipes/image.pipe";

@Component({
  selector: 'app-user-one',
  templateUrl: './user-one.component.html',
  styleUrls: ['./user-one.component.css'],
  providers: [UserService, RentService,ImagePipe]
})
export class UserOneComponent implements OnInit {
  editing:boolean= false;
  user: User = new User();
  rents: Rent[] = [];
  urlImage:string='';

  bonusType={
    'REGULAR':'',
    'BIRTHDAY':' на день народження',
    'ANNIVERSARY':'на річницю отримання карти',
    'PARTY':'на свято',
  }




  constructor(private _route: ActivatedRoute, private _user: UserService, private _rent: RentService, private img: ImagePipe) {
    _route.params.subscribe(next => {
      _user.findOne(next['id']).subscribe(next => {
        console.log(next);
        this.user = next;
        console.log(this.user);
        this.urlImage=this.img.transform(next.imagePath);
        this._rent.getAllByUserId(this.user.id).subscribe(next => {
          this.rents = next.reverse();
        }, error => {
          console.log(error);
        })
      }, error => {
        console.log(error);
      })
    });

  }
  getClosed(){
    let num=0;
    this.rents.forEach(next=>{
      if(next.rentStatus=='PAID')
        num+=1;
    });
    return num;
  }

  readUrl(event: any) {
    if (event.target.files && event.target.files[0]) {
      let reader = new FileReader();

      reader.onload = (event: any) => {
        this.urlImage = event.target.result;
      };

      reader.readAsDataURL(event.target.files[0]);
    }
  }

  revive() {
    this._user.revive(this.user.id).subscribe(next => {
      this.user = next;
    }, error => {
      console.log(error);
    })

  }

  updateUser(form:HTMLFormElement){
    this.editing=false;
    if(!isNullOrUndefined(this.user.cardId)&&this.user.cardId!=0&&!this.user.member) {
      this.user.dateOfMember=new Date().toISOString();
    }
    this._user.update(form,this.user).subscribe(next=>{
        this.user=next;
        form.reset();
        this.urlImage=this.img.transform(next.imagePath);
    },error => {
        console.log(error);
    })
  }
  delete(){
    this._user.delete(this.user.id).subscribe(next=>{
      this.user = next;
        console.log(next);
    },error => {
        console.log(error);
    })
  }

  calculateHours() {
    let hours = 0;
    this.rents.forEach(next => {
      if (next.rentStatus == 'PAID' || next.rentStatus == 'BONUSPAID')
        hours += next.duration != null && next.duration != undefined ? next.duration : 0;
    });
    return hours
  }

  calculateMoney() {
    let money = 0;
    this.rents.forEach(next => {
          money += next.rentStatus=='PAID'&&next.price!=null&&next.price!=undefined?next.price:0;
    });
    return money
  }

  isNoU(obj: any):boolean{

    return !isNullOrUndefined(obj);
  }

  ngOnInit() {
  }
  edit(){
    this.editing=!this.editing;
  }

}
