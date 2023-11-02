import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { LandingComponent } from './landing/landing.component';
import { OtpVerifyComponent } from './otp-verify/otp-verify.component';
import { ExpenseComponent } from './pages/dashboard/expense/expense.component';
import { FriendsComponent } from './pages/dashboard/friends/friends.component';
import { GroupsComponent } from './pages/dashboard/groups/groups.component';
import { HomeComponent } from './pages/dashboard/home/home.component';

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
    children:  [
      { path: 'home', component: HomeComponent },
      { path: 'addExpense', component: ExpenseComponent },
      { path: 'friends', component: FriendsComponent },
      { path: 'groups', component: GroupsComponent },
    ],
    // pathMatch:'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
