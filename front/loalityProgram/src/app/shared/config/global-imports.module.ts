import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {ScrollToModule} from "ng2-scroll-to";
import {HttpClientModule} from "@angular/common/http";
import {RouterModule, Routes} from '@angular/router';
import {mainRoutes} from "../../main.routes";
import {BrowserModule} from "@angular/platform-browser";


const _routes: Routes = [
  ...mainRoutes
];



@NgModule({
  imports: [
    ScrollToModule.forRoot(),
    RouterModule.forRoot(_routes, {useHash: true}),
  ],
  exports: [
    BrowserModule,
    HttpClientModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    ScrollToModule
  ],
  declarations: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class GlobalImportsModule {
}

