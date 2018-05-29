import {Component, OnInit} from '@angular/core';
import {FormArray, FormControl, FormGroup, Validators} from "@angular/forms";
import {phoneMask} from "../../../shared/config/config";
import {User} from "../../../shared/model/user";
import {UserService} from "../../../shared/service/user.service";
import {s} from "@angular/core/src/render3";
import {isNullOrUndefined} from "util";

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css'],
  providers: [UserService]
})
export class AddUserComponent implements OnInit {
  urlImg: string = 'empty';
  addMember: boolean = false;
  phoneMask = phoneMask;
  added: boolean = false;

  user: User = new User();
  userForm: FormGroup;

  constructor(private userService: UserService) {
  }

  readUrl(event: any) {
    if (event.target.files && event.target.files[0]) {
      let reader = new FileReader();

      reader.onload = (event: any) => {
        this.urlImg = event.target.result;
      };

      reader.readAsDataURL(event.target.files[0]);
    }
  }

  addUser(form: HTMLFormElement) {
    this.userService.save(form, this.user).subscribe(next => {
      console.log(next);
    }, error => {
      console.log(error);
    }, () => {
      this.added = true;
      setTimeout(() => {
        this.added = false;
      }, 2000)
    })
  }

  ngOnInit() {
    this.userForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      surname: new FormControl('', [Validators.required]),
      secondName: new FormControl('',),
      phone: new FormControl('', [Validators.required]),
      dateOfBirth: new FormControl('',),
      email: new FormControl('',),
      socialMedia: new FormControl(''),
      cardId: new FormControl('',),
    });
    this.userForm.valueChanges.subscribe(next => {
      this.user = next;
      if(!isNullOrUndefined(this.user.cardId)||this.user.cardId!=0){
        this.user.dateOfMember=new Date().toISOString();
      }
      console.log(this.user);
    })

  }

}
