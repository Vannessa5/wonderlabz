import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {ListComponent} from "./user/list/list.component";
import {EditComponent} from "./user/edit/edit.component";
import {ListCustomerComponent} from "./customer/list-customer/list-customer.component";
import {EditCustomerComponent} from "./customer/edit-customer/edit-customer.component";

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'user-list', component: ListComponent},
  {path: 'user-edit/:id', component: EditComponent},
  {path: 'user-create', component: EditComponent},
  {path: 'customer-list', component: ListCustomerComponent},
  {path: 'customer-create', component: EditCustomerComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
