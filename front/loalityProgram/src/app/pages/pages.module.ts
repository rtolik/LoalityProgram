import {NgModule} from '@angular/core';
import {PagesComponent} from "./pages/pages.component";
import {GlobalImportsModule} from "../shared/config/global-imports.module";
import {HeaderComponent} from "../source/header/header.component";
import {HomeComponent} from "./pages/home/home.component";
import { ClientsComponent } from './pages/clients/clients.component';
import { UserCardOneComponent } from './pages/clients/user-card-one/user-card-one.component';

@NgModule({
  imports: [
    GlobalImportsModule
  ],
  declarations: [PagesComponent, HeaderComponent, HomeComponent, ClientsComponent, UserCardOneComponent]
})
export class PagesModule {
}
