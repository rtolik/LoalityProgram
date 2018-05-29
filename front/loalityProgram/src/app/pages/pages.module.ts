import {NgModule} from '@angular/core';
import {PagesComponent} from "./pages/pages.component";
import {GlobalImportsModule} from "../shared/config/global-imports.module";
import {HeaderComponent} from "../source/header/header.component";
import {HomeComponent} from "./pages/home/home.component";
import { ClientsComponent } from './pages/clients/clients.component';
import { UserCardOneComponent } from './pages/clients/user-card-one/user-card-one.component';
import { AddUserComponent } from './pages/add-user/add-user.component';
import { UserOneComponent } from './pages/clients/user-one/user-one.component';
import { RentOneComponent } from './pages/clients/user-one/rent-one/rent-one.component';

@NgModule({
  imports: [
    GlobalImportsModule
  ],
  declarations: [PagesComponent, HeaderComponent, HomeComponent, ClientsComponent, UserCardOneComponent, AddUserComponent, UserOneComponent, RentOneComponent]
})
export class PagesModule {
}
