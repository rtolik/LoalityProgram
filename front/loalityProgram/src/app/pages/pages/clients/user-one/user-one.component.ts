import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-one',
  templateUrl: './user-one.component.html',
  styleUrls: ['./user-one.component.css']
})
export class UserOneComponent implements OnInit {
  editing:boolean= false;
  constructor() { }

  ngOnInit() {
  }
  edit(){
    this.editing=!this.editing;
  }

}
