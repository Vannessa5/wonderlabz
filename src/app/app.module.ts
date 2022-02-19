import {CUSTOM_ELEMENTS_SCHEMA, NgModule, NO_ERRORS_SCHEMA} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {HttpInterceptorService} from "./shared/http-interceptor/http-interceptor.service";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {LoginService} from "./login/service/login.service";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputTextModule} from "primeng/inputtext";
import {CalendarModule} from "primeng/calendar";
import {ProgressSpinnerModule} from "primeng/progressspinner";
import {ButtonModule} from "primeng/button";
import {DropdownModule} from "primeng/dropdown";
import {TableModule} from "primeng/table";
import {BreadcrumbModule} from "primeng/breadcrumb";
import {PasswordModule} from "primeng/password";
import {MultiSelectModule} from "primeng/multiselect";
import {MessageService} from "primeng/api";
import {ListComponent} from './user/list/list.component';
import {EditComponent} from './user/edit/edit.component';
import {UserService} from "./user/shared/user.service";
import {MenubarModule} from "primeng/menubar";
import {InputMaskModule} from "primeng/inputmask";
import {ListCustomerComponent} from './customer/list-customer/list-customer.component';
import {CustomerService} from "./customer/shared/customer-service";
import {EditCustomerComponent} from './customer/edit-customer/edit-customer.component';
import {RadioButtonModule} from 'primeng/radiobutton';
import {DialogModule} from 'primeng/dialog';


@NgModule({
  declarations: [
    AppComponent, LoginComponent, EditComponent, ListComponent, EditComponent, ListCustomerComponent, EditCustomerComponent
  ],
  imports: [
    BrowserModule, AppRoutingModule, HttpClientModule, BrowserAnimationsModule, FormsModule, ReactiveFormsModule, InputTextModule,
    CalendarModule, ProgressSpinnerModule, ButtonModule, DropdownModule, TableModule, BreadcrumbModule, PasswordModule,
    MultiSelectModule, MenubarModule, InputMaskModule, RadioButtonModule, DialogModule

  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: HttpInterceptorService,
    multi: true
  }, LoginService, MessageService, UserService, CustomerService],
  bootstrap: [AppComponent],
  schemas: [NO_ERRORS_SCHEMA, CUSTOM_ELEMENTS_SCHEMA]

})
export class AppModule {
}
