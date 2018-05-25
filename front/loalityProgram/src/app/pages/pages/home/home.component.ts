import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {TestService} from "../../../shared/service/test.service";
import {RentService} from "../../../shared/service/rent.service";
import {timeMask} from "../../../shared/config/config";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [RentService]
})
export class HomeComponent implements OnInit {
  dateStr : string=new Date().toISOString().substring(0,10);
  arr: Array<string> = new Array<string>(24);
  mask = timeMask;
  selectedHours: number = 0;
  selectedTime: string = '';

  constructor(private _rent: RentService) {
    for (let i = 0; i < this.arr.length; i++) {
      this.arr[i] = new Date(0, 0, 0, i + 2, 0, 0, 0).toISOString().substring(11, 16) + '-' + new Date(0, 0, 0, i + 3, 0, 0, 0).toISOString().substring(11, 16);
    }
  }

  addHalfAndHour(time: string, el: HTMLDivElement) {

    if (el.classList.contains('background-orange')) {
      if (
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
      el.parentElement.parentElement.parentElement.previousElementSibling.lastElementChild.firstElementChild.lastElementChild.classList.contains('background-orange')
      &&
      el.classList.contains('border-bottom')
      ||
      document.getElementsByClassName('background-orange').length >= 1
      &&
      !el.classList.contains('border-bottom')
      &&
      el.previousElementSibling.classList.contains('background-orange')
      ||
      document.getElementsByClassName('background-orange').length >= 1
      &&
      el.parentElement.parentElement.parentElement.nextElementSibling.lastElementChild.firstElementChild.firstElementChild.classList.contains('background-orange')
      &&
      !el.classList.contains('border-bottom')
      ||
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


  getDate(date :string){

  }

  ngOnInit() {
  }

}
