import {NgModule} from '@angular/core';
import {PagesComponent} from "./pages/pages.component";
import {GlobalImportsModule} from "../shared/config/global-imports.module";
import {HeaderComponent} from "../source/header/header.component";
import {HomeComponent} from "./pages/home/home.component";

@NgModule({
  imports: [
    GlobalImportsModule
  ],
  declarations: [PagesComponent, HeaderComponent, HomeComponent]
})
export class PagesModule {
}
