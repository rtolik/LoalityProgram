import {Component, OnInit, ViewChild} from '@angular/core';
import {FormArray, FormControl, FormGroup, Validators} from "@angular/forms";
import {phoneMask} from "../../../shared/config/config";
import {User} from "../../../shared/model/user";
import {UserService} from "../../../shared/service/user.service";
import {s} from "@angular/core/src/render3";
import {isNullOrUndefined} from "util";
import {BonusDay} from "../../../shared/model/bonus-day";
import {BonusDayService} from "../../../shared/service/bonus-day.service";

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css'],
  providers: [UserService,BonusDayService]
})
export class AddUserComponent implements OnInit {
  urlImg: string = 'empty';
  addMember: boolean = false;
  phoneMask = phoneMask;
  added: boolean = false;
  partyBool = false;
  user: User = new User();
  userForm: FormGroup;
  bonusDayForm: FormGroup;
  bonusDay: BonusDay = new BonusDay();
  bonusDays: BonusDay[] = [];

  constructor(private userService: UserService,private _bonus:BonusDayService) {
    this._bonus.findAllDays().subscribe(next => {
      this.bonusDays = next;
    }, error => {
      console.log(error);
    })
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
      this.userForm.reset();
    }, error => {
      console.log(error);
    }, () => {
      this.added = true;
      this.urlImg='empty';
      setTimeout(() => {
        this.added = false;
      }, 2000)
    })
  }


  deleteDay(id:number){
  this._bonus.deleteBonusDay(id).subscribe(next=>{
      this.bonusDays.forEach((next,ind)=>{
        if(next.id==id){
          this.bonusDays.splice(ind,1);
        }
      })
  },error => {
      console.log(error);
  })
  }
  addDay() {
    this._bonus.saveBonusDay(this.bonusDay).subscribe(next => {
      this.bonusDays.push(next);
      this.bonusDayForm.reset();
    }, error => {
      console.log(error);
    })

  }
  ngOnInit() {
    this.userForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      surname: new FormControl('', [Validators.required]),
      secondName: new FormControl('',),
      phone: new FormControl('', [Validators.required, Validators.minLength(12)]),
      dateOfBirth: new FormControl('',),
      email: new FormControl('',),
      socialMedia: new FormControl(''),
      cardId: new FormControl('',),
    });
    this.bonusDayForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      date: new FormControl('', [Validators.required]),
      bonusesToAdd: new FormControl('', [Validators.required]),
    });
    this.bonusDayForm.valueChanges.subscribe(next => {
      this.bonusDay = next;
    });
    this.userForm.valueChanges.subscribe(next => {
      this.user = next;
      if(!isNullOrUndefined(this.user.cardId)&&this.user.cardId!=0){
        this.user.dateOfMember=new Date().toISOString();
      }
      if (this.user.dateOfBirth == '')
        this.user.dateOfBirth = null;
      if (this.user.secondName == '')
        this.user.secondName = null;
      if (this.user.socialMedia == '')
        this.user.socialMedia = null;
      if (this.user.email == '')
        this.user.email = null;
      this.user.dateOfRegistration=new Date().toISOString();
    })

  }

}
