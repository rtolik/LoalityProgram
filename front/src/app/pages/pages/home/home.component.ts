import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  dateStr : string=new Date().toISOString().substring(0,10);
  constructor() {

  }

  getDate(date :string){
    console.log(date);
  }

  ngOnInit() {
  }

}
