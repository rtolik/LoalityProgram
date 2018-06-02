import {Component, ElementRef, OnInit, QueryList, ViewChild, ViewChildren} from '@angular/core';
import {TestService} from "../../../shared/service/test.service";
import {RentService} from "../../../shared/service/rent.service";
import {timeMask} from "../../../shared/config/config";
import {isNullOrUndefined} from "util";
import {Rent} from "../../../shared/model/rent";
import {Subject} from "rxjs/Subject";
import {UserService} from "../../../shared/service/user.service";
import {User} from "../../../shared/model/user";
import {forEach} from "@angular/router/src/utils/collection";
import {Bonus} from "../../../shared/model/bonus";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [RentService,UserService]
})
export class HomeComponent implements OnInit {
  rentEnum = {
    'AWAIT': 'Очікується',
    'LEAVED': 'Покинута',
    'BONUSPAID': 'Бонус',
    'PAID': 'Оплачено'
  };

  @ViewChild('cell') cell: HTMLDivElement;
  dateStr : string=new Date().toISOString().substring(0,10);
  arr: Array<string> = new Array<string>(24);
  mask = timeMask;
  selectedHours: number = 0;
  selectedTime: string = '';
  rent: Rent[] = [];
  user: User[]=[];
  // rent = [{
  //   id: 1,
  //   duration: 2,
  //   timeOfStart: '18:00',
  //   timeOfEnd: '20:00',
  //   rentStatus: 'AWAIT',
  //   user: {
  //     id: 1,
  //     surname: 'Petryk',
  //     name: 'Oleh',
  //     secondName: 'Jaroslab',
  //     phone: '0123213',
  //     isMember: true,
  //   }
  // }
  // ];
  constructor(private _rent: RentService, private _user : UserService) {
    for (let i = 0; i < this.arr.length; i++) {
      this.arr[i] = new Date(0, 0, 0, i + 2, 0, 0, 0).toISOString().substring(11, 16) + '-' + new Date(0, 0, 0, i + 3, 0, 0, 0).toISOString().substring(11, 16);
    }
      _rent.getAllByDate(this.dateStr).subscribe(next => {
      this.rent = next;
      console.log(this.rent);
    }, error => {
      console.log(error);
    });
    this._user.findUserFormRent().subscribe(next=>{
      this.user=next;
    },error => {
        console.log(error);
    });
  }

  addHalfAndHour(time: string, el: HTMLDivElement) {
    time = time.substring(0, 5);
    if (!el.classList.contains('reserved') && !el.classList.contains('closed')) {
    if (el.classList.contains('background-orange')) {
      if (
        el.classList.contains('border-bottom')
        &&
        el.parentElement.parentElement.parentElement.previousElementSibling.classList.contains('heading')
        ||
        el.classList.contains('border-bottom')
        &&
        !el.nextElementSibling.classList.contains('background-orange')
        ||
        el.classList.contains('border-bottom')
        &&
        !el.parentElement.parentElement.parentElement.previousElementSibling.lastElementChild.firstElementChild.lastElementChild.classList.contains('background-orange')
        ||
        !el.classList.contains('border-bottom')
        &&
        !el.previousElementSibling.classList.contains('background-orange')
        ||
        !el.classList.contains('border-bottom')
        &&
        isNullOrUndefined(el.parentElement.parentElement.parentElement.nextElementSibling)
        ||
        !el.classList.contains('border-bottom')
        &&
        !el.parentElement.parentElement.parentElement.nextElementSibling.lastElementChild.firstElementChild.firstElementChild.classList.contains('background-orange')
      ) {
        el.classList.toggle('background-orange');
        this.selectedHours -= 0.5;
        if (this.selectedHours == 0) {
          this.selectedTime = '';
        }
      }
    } else if (document.getElementsByClassName('background-orange').length == 0) {
      el.classList.toggle('background-orange');
      if (this.selectedHours < 0.5) {
        this.selectedTime = time.substring(0, 5);
        this.selectedHours = 0.5
      } else {
        this.selectedHours += 0.5
      }
    } else if (
      document.getElementsByClassName('background-orange').length >= 1
      &&
      el.classList.contains('border-bottom')
      &&
      el.nextElementSibling.classList.contains('background-orange')
      ||
      document.getElementsByClassName('background-orange').length >= 1
      &&
      !el.classList.contains('border-bottom')
      &&
      el.previousElementSibling.classList.contains('background-orange')
      ||
      document.getElementsByClassName('background-orange').length >= 1
      &&
      el.classList.contains('border-bottom')
      &&
      el.parentElement.parentElement.parentElement.previousElementSibling.lastElementChild.firstElementChild.lastElementChild.classList.contains('background-orange')
      ||
      document.getElementsByClassName('background-orange').length >= 1
      &&
      !el.classList.contains('border-bottom')
      &&
      el.parentElement.parentElement.parentElement.nextElementSibling.lastElementChild.firstElementChild.firstElementChild.classList.contains('background-orange')
      ||
      document.getElementsByClassName('background-orange').length >= 1
      &&
      el.classList.contains('border-bottom')
      &&
      el.nextElementSibling.classList.contains('background-orange')
    ) {
      el.classList.toggle('background-orange');
      if (this.selectedHours < 0.5) {
        this.selectedTime = time.substring(0, 5);
        this.selectedHours = 0.5
      } else {
        this.selectedHours += 0.5
      }
    }
    for (let i = 0; i < document.getElementsByClassName('one-plus-cell').length; i++) {
      if (document.getElementsByClassName('one-plus-cell')[i].classList.contains('background-orange')) {
        if (!document.getElementsByClassName('one-plus-cell')[i].classList.contains('border-bottom'))
          this.selectedTime = document.getElementsByClassName('one-plus-cell')[i].parentElement.parentElement.previousElementSibling.innerHTML.substring(0, 3) + '30';
        else
          this.selectedTime = document.getElementsByClassName('one-plus-cell')[i].parentElement.parentElement.previousElementSibling.innerHTML.substring(0, 5);
        break;
      }
    }
    }
  }

  getName(startTime: string, cell: HTMLDivElement, upName?: HTMLParagraphElement, current?: HTMLDivElement) {
    if (upName && current) {
        upName.parentElement.style.display = 'block';
        current.classList.add('h-50')
        current.parentElement.classList.remove('justify-content-center');
        current.style.fontSize = '14px';
        upName.style.fontSize='14px';
    }

    startTime = startTime.substring(0, 5);
    if (!cell.classList.contains('border-bottom')) {
      startTime = startTime.substring(0, 2) + ':30';
    }
    let str = '';
      this.rent.forEach(next => {
        if (
          new Date(0, 0, 0, parseInt(next.timeOfStart.substring(0, 2)), next.timeOfStart.substring(3, 5) == '00' ? 0 : 30) <= new Date(0, 0, 0, parseInt(startTime.substring(0, 2)), startTime.substring(3, 5) == '00' ? 0 : 30)
          &&
          new Date(0, 0, 0, parseInt(startTime.substring(0, 2)), startTime.substring(3, 5) == '00' ? 0 : 30) < new Date(0, 0, 0, parseInt(next.timeOfEnd.substring(0, 2)), next.timeOfEnd.substring(3, 5) == '00' ? 0 : 30)
        ) {
          str = next.user.name + ' ' + next.user.surname;
          if(next.user.isMember) {
            str+=" *"
          }
          if (upName && current) {
            if (upName.innerText == str) {
              upName.parentElement.style.display = 'none';
              current.classList.remove('h-50')
              current.parentElement.classList.add('justify-content-center');
              current.style.fontSize = '18px';
            }
          }
        }
    });
    return str
  }

  getPhone(startTime: string, cell: HTMLDivElement, upName?: HTMLParagraphElement, current?: HTMLDivElement) {
    if (upName && current) {
      upName.parentElement.style.display = 'block';
      current.classList.add('h-50');
      current.parentElement.classList.remove('justify-content-center');
      current.style.fontSize = '14px';
      upName.style.fontSize='14px';
    }
    startTime = startTime.substring(0, 5);
    if (!cell.classList.contains('border-bottom')) {
      startTime = startTime.substring(0, 2) + ':30';
    }
    let str = '';
      this.rent.forEach(next => {
        if (
          new Date(0, 0, 0, parseInt(next.timeOfStart.substring(0, 2)), next.timeOfStart.substring(3, 5) == '00' ? 0 : 30) <= new Date(0, 0, 0, parseInt(startTime.substring(0, 2)), startTime.substring(3, 5) == '00' ? 0 : 30)
          &&
          new Date(0, 0, 0, parseInt(startTime.substring(0, 2)), startTime.substring(3, 5) == '00' ? 0 : 30) < new Date(0, 0, 0, parseInt(next.timeOfEnd.substring(0, 2)), next.timeOfEnd.substring(3, 5) == '00' ? 0 : 30)
        ) {
          str = next.user.phone;
          if (upName && current) {
            if (upName.innerText == str) {
              upName.parentElement.style.display = 'none';
              current.classList.remove('h-50');
              current.parentElement.classList.add('justify-content-center');
              current.style.fontSize = '18px';
            }
          }
        }
    });
    return str
  }

  getRentStatus(startTime: string, cell: HTMLDivElement, upName?: HTMLParagraphElement, current?: HTMLDivElement,currentStatus?: HTMLDivElement,upStatus?:HTMLDivElement) {
    if (upName && current) {
      currentStatus.parentElement.style.display = 'block';
      currentStatus.classList.add('h-50');
      currentStatus.parentElement.classList.remove('justify-content-center');
      currentStatus.style.fontSize = '14px';
      upStatus.style.fontSize='14px';
    }
    startTime = startTime.substring(0, 5);
    if (!cell.classList.contains('border-bottom')) {
      startTime = startTime.substring(0, 2) + ':30';
    }
    let str = '';
      this.rent.forEach(next => {
        if (
          new Date(0, 0, 0, parseInt(next.timeOfStart.substring(0, 2)), next.timeOfStart.substring(3, 5) == '00' ? 0 : 30) <= new Date(0, 0, 0, parseInt(startTime.substring(0, 2)), startTime.substring(3, 5) == '00' ? 0 : 30)
          &&
          new Date(0, 0, 0, parseInt(startTime.substring(0, 2)), startTime.substring(3, 5) == '00' ? 0 : 30) < new Date(0, 0, 0, parseInt(next.timeOfEnd.substring(0, 2)), next.timeOfEnd.substring(3, 5) == '00' ? 0 : 30)
        ) {
          str = this.rentEnum[next.rentStatus];
          if (upName && current&&currentStatus&&upStatus) {
            if (upName.innerText == current.innerText) {
              upStatus.parentElement.style.display = 'none';
              currentStatus.classList.remove('h-50');
              currentStatus.parentElement.classList.add('justify-content-center');
              currentStatus.style.fontSize = '16px';
            }
          }
        }
    });
    return str
  }

  findRentClass(startTime: string, cell: HTMLDivElement): string {
    cell.classList.remove('reserved');
    cell.classList.remove('closed');
    startTime = startTime.substring(0, 5);
    if (!cell.classList.contains('border-bottom')) {
      startTime = startTime.substring(0, 2) + ':30';
    }
      this.rent.forEach(next => {
        if (
          new Date(0, 0, 0, parseInt(next.timeOfStart.substring(0, 2)), next.timeOfStart.substring(3, 5) == '00' ? 0 : 30) <= new Date(0, 0, 0, parseInt(startTime.substring(0, 2)), startTime.substring(3, 5) == '00' ? 0 : 30)
          &&
          new Date(0, 0, 0, parseInt(startTime.substring(0, 2)), startTime.substring(3, 5) == '00' ? 0 : 30) < new Date(0, 0, 0, parseInt(next.timeOfEnd.substring(0, 2)), next.timeOfEnd.substring(3, 5) == '00' ? 0 : 30)
        ) {
          if (next.rentStatus == 'AWAIT')
            cell.classList.add('reserved');
          else
            cell.classList.add('closed')
        }
      });
    return ''
  }

  getDate(date :string){
    for (let i=0; i< document.getElementsByClassName('one-plus-cell').length;i++) {
      document.getElementsByClassName('one-plus-cell')[i].classList.remove('background-orange');
    }
    this.arr = new Array<string>(24);
    for (let i = 0; i < this.arr.length; i++) {
      this.arr[i] = new Date(0, 0, 0, i + 2, 0, 0, 0).toISOString().substring(11, 16) + '-' + new Date(0, 0, 0, i + 3, 0, 0, 0).toISOString().substring(11, 16);
    }

    this.selectedHours=0;
    console.log(this.dateStr);
    this._rent.getAllByDate(this.dateStr).subscribe(next => {
      this.rent=next;
      console.log(this.rent);
    }, error => {
      console.log(error);
    },()=>{

    })
  }

  newRent : Rent = new Rent();
  userId: number=0;
  added: boolean = false;
  getUserId(str:string): void{
    this.userId = parseInt(str.split('|')[0]);
  }
  addRent(){
    this.newRent.date=this.dateStr;
    this.newRent.timeOfStart=this.selectedTime;
    this.newRent.duration=this.selectedHours;
    console.log(this.newRent);
    this._rent.save(this.newRent,this.userId).subscribe(next=>{
        this.newRent= new Rent();
        this.selectedTime='';
        this.selectedHours=0;
    },error => {
        console.log(error);
    },()=>{
      this.added=true;
      setTimeout(this.added=false,2000)
      this.getDate(this.dateStr);
    })

  }


  ngOnInit() {
  }

}
