import {Injectable} from "@angular/core";
import {HttpClient, HttpContext} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {environment} from "../../../environments/environment";
import {AUTHORIZATION} from "../../shared/http-interceptor/http-interceptor.service";

@Injectable()
export class UserService {

  constructor(protected http: HttpClient) {

  }


  getToken(): any {
    return window.localStorage.getItem('token');
  }

  saveUser(user: any): Observable<any> {
    return this.http.post<any>(environment.USER_URL, user);
  }

  updateUser(user: any): Observable<any> {
    return this.http.put<any>(environment.USER_URL, user);
  }

  getUserById(id: number): Observable<any> {
    return this.http.get<any>(`${environment.USER_URL}/${id}`);
  }
  getUserByUsername(username: string): Observable<any> {
    return this.http.get<any>(`${environment.USER_URL}/${username}`);
  }

  getAllUsers(): Observable<any[]> {
    return this.http.get<any[]>(`${environment.USER_URL}`,
      {
        context: new HttpContext().set(AUTHORIZATION, `Bearer ${this.getToken()}`)
      });
  }


  deleteUser(id: number): Observable<any> {
    return this.http.delete<any>(`${environment.USER_URL}/${id}`, {
      context: new HttpContext().set(AUTHORIZATION, `Bearer ${this.getToken()}`)
    });
  }


}
