import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../../../shared/model/user";

@Component({
  selector: 'app-user-card-one',
  templateUrl: './user-card-one.component.html',
  styleUrls: ['./user-card-one.component.css']
})
export class UserCardOneComponent implements OnInit {
  @Input() user: User;
  constructor() { }

  ngOnInit() {
  }

}
