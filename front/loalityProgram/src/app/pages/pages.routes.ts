import {Routes} from "@angular/router";
import {PagesComponent} from "./pages/pages.component";
import {HomeComponent} from "./pages/home/home.component";
import {ClientsComponent} from "./pages/clients/clients.component";

export const pagesRoutes: Routes = [
  {
    path: '', component: PagesComponent, children: [
      {
        path: '', component: HomeComponent
      },
      {
        path: 'clients', component: ClientsComponent
      }
    ]
  }
];
