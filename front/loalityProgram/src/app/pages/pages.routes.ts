import {Routes} from "@angular/router";
import {PagesComponent} from "./pages/pages.component";
import {HomeComponent} from "./pages/home/home.component";
import {ClientsComponent} from "./pages/clients/clients.component";
import {AddUserComponent} from "./pages/add-user/add-user.component";
import {UserOneComponent} from "./pages/clients/user-one/user-one.component";
import {RentOneComponent} from "./pages/clients/user-one/rent-one/rent-one.component";
import {RentOnePageComponent} from "./pages/rent-one-page/rent-one-page.component";
import {FinancesComponent} from "./pages/finances/finances.component";
import {LogInComponent} from "../source/log-in/log-in.component";
import {LogInCanActive} from "../shared/can-active/log-in.can-active";

export const pagesRoutes: Routes = [
  {
    path: '', component: PagesComponent,canActivate:[LogInCanActive] ,children: [
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
      },
      {
        path: 'rent/:id', component: RentOnePageComponent
      },
      {
        path: 'finances', component: FinancesComponent
      }

    ]
  }
];
