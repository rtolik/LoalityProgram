import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import {GlobalImportsModule} from "./shared/config/global-imports.module";
import {PagesModule} from "./pages/pages.module";
import {RouterModule, Routes} from "@angular/router";
import {mainRoutes} from "./main.routes";
import {ScrollToModule} from "ng2-scroll-to";


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    GlobalImportsModule,
    PagesModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
