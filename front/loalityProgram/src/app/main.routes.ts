import {Routes} from "@angular/router";
import {AppComponent} from "./app.component";
import {pagesRoutes} from "./pages/pages.routes";
import {LogInComponent} from "./source/log-in/log-in.component";

export const mainRoutes: Routes = [
  {
    path: '', component: AppComponent, children: [
      ...pagesRoutes,
      {
        path:'log-in',component:LogInComponent
      }
    ]
  }
];
