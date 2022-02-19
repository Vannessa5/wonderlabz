import {Injectable} from '@angular/core';
import {HttpClient, HttpContext, HttpHeaders} from "@angular/common/http";
import {Login} from "../../login/service/login.model";
import {Observable} from "rxjs";
import {AUTHORIZATION} from "../../shared/http-interceptor/http-interceptor.service";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(public http: HttpClient) {

  }

  authenticate(login: Login): Observable<any> {
    return this.http.post<any>(environment.LOGIN_URL, login,
      {
        context: new HttpContext().set(AUTHORIZATION, `Bearer `)
      })

  }
}
