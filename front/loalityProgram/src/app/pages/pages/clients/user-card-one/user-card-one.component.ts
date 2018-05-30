import {Component, Input, OnInit} from '@angular/core';
import {User} from "../../../../shared/model/user";
import {ImagePipe} from "../../../../shared/pipe/pipes/image.pipe";

@Component({
  selector: 'app-user-card-one',
  templateUrl: './user-card-one.component.html',
  styleUrls: ['./user-card-one.component.css'],
  providers: [ImagePipe]
})
export class UserCardOneComponent implements OnInit {
  @Input() user: User;
  constructor(private  imagePipe: ImagePipe) {
    console.log(this.user);
  }

  ngOnInit() {
  }

}
