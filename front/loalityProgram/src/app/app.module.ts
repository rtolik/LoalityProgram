import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {GlobalImportsModule} from "./shared/config/global-imports.module";
import {PagesModule} from "./pages/pages.module";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {ContentInterceptor} from "./shared/interceptor/content-interceptor";
import {LogInComponent} from './source/log-in/log-in.component';
import {LogInCanActive} from "./shared/can-active/log-in.can-active";
import {UserService} from "./shared/service/user.service";


@NgModule({
  declarations: [
    AppComponent,
    LogInComponent
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
    },
    UserService,
    LogInCanActive
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
