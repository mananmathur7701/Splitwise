import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { BackServicesService } from './back-services.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  isUser = false;
  constructor(private router:Router, private login:BackServicesService){}
  canActivate() {
    if(this.login.isLoggedIn())
    {
    return true;
    }
    else
    {
      this.router.navigateByUrl('/login');
      return false;
    }
  }
}