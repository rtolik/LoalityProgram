import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import {GlobalImportsModule} from "./shared/config/global-imports.module";
import {PagesModule} from "./pages/pages.module";
import {RouterModule, Routes} from "@angular/router";
import {mainRoutes} from "./main.routes";
import {ScrollToModule} from "ng2-scroll-to";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {ContentInterceptor} from "./shared/interceptor/content-interceptor";
import { ImagePipe } from './shared/pipe/pipes/image.pipe';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    GlobalImportsModule,
    PagesModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ContentInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
