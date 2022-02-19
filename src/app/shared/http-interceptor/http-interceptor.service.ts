import {Injectable, OnInit} from '@angular/core';
import {
  HttpContextToken,
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError, retry} from "rxjs/operators";

export let AUTHORIZATION = new HttpContextToken(() => `Bearer ${window.localStorage.getItem('token')}`);

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorService implements HttpInterceptor, OnInit {


  constructor() {
  }

  ngOnInit(): void {
    const authorization = `Bearer ${window.localStorage.getItem('token')}`;
    AUTHORIZATION = new HttpContextToken(() => `Bearer ${authorization}`);
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const modifiedRequest = request.clone({
      headers: request.headers
        .set('Content-Type', "application/json")
        .set('charset', "UTF-8")
        .set('Authorization', request.context.get(AUTHORIZATION))
    });
    return next.handle(modifiedRequest)
      .pipe(retry(1),
        catchError((error: HttpErrorResponse) => {
          return throwError(error)
        }));
  }
}
