import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { LandingComponent } from './landing/landing.component';
import { OtpVerifyComponent } from './otp-verify/otp-verify.component';

const routes: Routes = [
  {
    path:'',
    component: LandingComponent,
    pathMatch:'full'
  },
  {
    path:'register',
    component: RegisterComponent,
    pathMatch:'full'
  },
  {
    path:'otp',
    component: OtpVerifyComponent,
    pathMatch:'full'
  },
  {
    path:'login',
    component: LoginComponent,
    pathMatch:'full'
  },
  {
    path:'dashboard',
    component: DashboardComponent,
    pathMatch:'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
