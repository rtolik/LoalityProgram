import {Component, Input, OnInit} from '@angular/core';
import {Rent} from "../../../../../shared/model/rent";

@Component({
  selector: 'app-rent-one',
  templateUrl: './rent-one.component.html',
  styleUrls: ['./rent-one.component.css']
})
export class RentOneComponent implements OnInit {
  @Input() rent: Rent;
  constructor() { }

  ngOnInit() {
  }

}
