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
import {RentOnePageComponent} from "./pages/rent-one-page/rent-one-page.component";
import { FinancesComponent } from './pages/finances/finances.component';
import {ImagePipe} from "../shared/pipe/pipes/image.pipe";

@NgModule({
  imports: [
    GlobalImportsModule
  ],
  declarations: [PagesComponent, HeaderComponent, HomeComponent, ClientsComponent, UserCardOneComponent, AddUserComponent, UserOneComponent, RentOneComponent, RentOnePageComponent, FinancesComponent]
})
export class PagesModule {
}
