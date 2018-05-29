import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-rent-one-page',
  templateUrl: './rent-one-page.component.html',
  styleUrls: ['./rent-one-page.component.css']
})
export class RentOnePageComponent implements OnInit {
  editing: boolean=false;
  constructor() { }

  edit(){
    this.editing=!this.editing;
  }
  ngOnInit() {
  }

}
