import {Routes} from "@angular/router";
import {PagesComponent} from "./pages/pages.component";
import {HomeComponent} from "./pages/home/home.component";
import {ClientsComponent} from "./pages/clients/clients.component";
import {AddUserComponent} from "./pages/add-user/add-user.component";
import {UserOneComponent} from "./pages/clients/user-one/user-one.component";

export const pagesRoutes: Routes = [
  {
    path: '', component: PagesComponent, children: [
      {
        path: '', component: HomeComponent
      },
      {
        path: 'clients', component: ClientsComponent
      },
      {
        path: 'add-user', component: AddUserComponent
      },
      {
        path: 'user/:id', component: UserOneComponent
      }
    ]
  }
];
