import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {RentService} from "../../../shared/service/rent.service";
import {Rent} from "../../../shared/model/rent";
import {timeMask} from "../../../shared/config/config";
import {Location} from '@angular/common';

@Component({
  selector: 'app-rent-one-page',
  templateUrl: './rent-one-page.component.html',
  styleUrls: ['./rent-one-page.component.css'],
  providers: [RentService]
})
export class RentOnePageComponent implements OnInit {
  rentEnum = {
    'AWAIT': 'Очікується',
    'LEAVED': 'Покинута',
    'BONUSPAID': 'Бонус',
    'PAID': 'Оплачено'
  };
  editing: boolean=false;
  rent: Rent = new Rent();
  mask = timeMask;
  price: number = 0;
  bonus: number = 0;
  bool = false;
  rentClosed: boolean;

  constructor(private router: ActivatedRoute, private _rent: RentService,private location: Location) {
    this.router.params.subscribe(next => {
      _rent.findOne(next['id']).subscribe(next => {
        console.log(next);
        this.rent = next;
        console.log(this.rent);
        this.bool = true;
        this.rentClosed = this.rent.rentStatus == 'PAID'||this.rent.rentStatus=='LEAVED'||this.rent.rentStatus=='BONUSPAID'
      }, error => {
        console.log(error);
      })
    })
  }

  updateRent() {
    this._rent.updateRent(this.rent).subscribe(next => {
      this.rent = next
      this.editing=!this.editing
    }, error => {
      console.log(error);
    })
  }

  submit() {
    console.log(this.bonus);
    console.log(this.price);
    if (this.bonus != 0 || this.price != 0) {
      console.log(`${this.rent.id}, ${this.price}, ${this.bonus}`);
      this._rent.submitRent(this.rent.id, this.price, this.bonus).subscribe(next => {
        this.rent = next;
        console.log('ahsbdhsad',this.rent);
        this.rentClosed = true;
      }, error => {
        console.log(error);
      })
    }
  }
  leaved(){
    this._rent.leave(this.rent.id).subscribe(next=>{
        this.rent=next;
        this.rentClosed=true;
    },error => {
        console.log(error);
    })

  }
  delete(){
    this._rent.delete(this.rent.id).subscribe(next=>{
      this.rent=new Rent();
      this.location.back();
    },error => {
        console.log(error);
    })
  }

  gerAllBonus() {
    let numb = 0;
    this.rent.user.bonuses.forEach(next => {
      numb += next.value;
    });
    return numb;
  }


  edit(){
    this.editing=!this.editing;
  }
  ngOnInit() {
  }

}
