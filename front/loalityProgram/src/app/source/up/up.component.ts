import {Component, OnInit } from '@angular/core';

@Component({
    selector: 'app-up',
    templateUrl: './up.component.html',
    styleUrls: ['./up.component.css']
})
export class UpComponent implements OnInit {

    constructor() {
    }

    kek = false;

    ngOnInit() {
        document.addEventListener('scroll', this.visible);
    }

    visible() {
        if (this.kek == true) {
            if (window.pageYOffset <= window.innerHeight) {

                document.getElementById("hav-panel").style.animation = "hide .5s";
                document.getElementById("hav-panel").style.bottom = "-20vh";
            }
        }
        if(window.pageYOffset > window.innerHeight){
            document.getElementById("hav-panel").style.animation = "show .5s";
            document.getElementById("hav-panel").style.bottom = "0";
            this.kek = true;
        }
    }
}
