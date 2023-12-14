import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent {
  constructor(private router:Router) { 
    localStorage.clear();
  }

  ngOnInit(): void {}
  
  tologin(){
    this.router.navigateByUrl('/login');
  }
  tosignup(){
    this.router.navigateByUrl('/register');
  }

}


