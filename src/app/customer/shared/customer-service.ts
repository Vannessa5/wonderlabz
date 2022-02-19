import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs/Observable";


@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(protected http: HttpClient) {
  }

  createCustomer(customer: any) {
    return this.http.post(`${environment.CUSTOMERS_URL}`, customer)
  }

  updateCustomer(customer: any) {

  }

  deposit(customer: any) {
    return this.http.put(`${environment.CUSTOMERS_URL}/deposit`, customer)
  }

  withdraw(customer: any) {
    return this.http.put(`${environment.CUSTOMERS_URL}/withdrawal`, customer)
  }


  getAllCustomers(): Observable<any> {
    return this.http.get(`${environment.CUSTOMERS_URL}`)
  }


}
