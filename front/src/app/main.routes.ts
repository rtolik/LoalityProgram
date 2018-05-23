import {Routes} from "@angular/router";
import {AppComponent} from "./app.component";
import {pagesRoutes} from "./pages/pages.routes";

export const mainRoutes: Routes = [
  {
    path: '', component: AppComponent, children: [
      ...pagesRoutes
    ]
  }
];
