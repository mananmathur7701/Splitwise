import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HTTP_INTERCEPTORS
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { BackServicesService } from './back-services.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    constructor(private login:BackServicesService){}
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        console.log("inside interceptor");
        //***Add Jwt Token To request**** */
        let authReq= req;
        const token = this.login.getToken();
        console.log(`token is ${token}`);
        if(token!=null){
            authReq=authReq.clone({
                setHeaders:{Authorization: `Bearer ${token}`},
            });
        }
        return next.handle(authReq);
    }
}
export const authInterceptorProviders= [
    {
        provide:HTTP_INTERCEPTORS,
        useClass: AuthInterceptor,
        multi:true,
    },
];



