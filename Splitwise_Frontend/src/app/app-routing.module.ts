import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { LandingComponent } from './landing/landing.component';
import { OtpVerifyComponent } from './otp-verify/otp-verify.component';
import { ExpenseComponent } from './pages/dashboard/groups/expense/expense.component';
import { FriendsComponent } from './pages/dashboard/friends/friends.component';
import { GroupsComponent } from './pages/dashboard/groups/groups.component';
import { HomeComponent } from './pages/dashboard/home/home.component';
import { AddGroupComponent } from './pages/dashboard/add-group/add-group.component';
import { GroupDetailsComponent } from './pages/dashboard/group-details/group-details.component';
import { ProfileComponent } from './pages/dashboard/profile/profile.component';
import { FriendDetailsComponent } from './pages/dashboard/friends/friend-details/friend-details.component';

const routes: Routes = [
  {
    path: '',
    component: LandingComponent,
    pathMatch: 'full',
  },
  {
    path: 'register',
    component: RegisterComponent,
    pathMatch: 'full',
  },
  {
    path: 'otp',
    component: OtpVerifyComponent,
    pathMatch: 'full',
  },
  {
    path: 'login',
    component: LoginComponent,
    pathMatch: 'full',
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    children: [
      { path: 'home', component: HomeComponent },
      { path: 'friends', component: FriendsComponent },
      { path: 'groups',component: GroupsComponent},
      { path: 'addExpense', component: ExpenseComponent },
      { path: 'addGroup', component: AddGroupComponent },
      { path: 'group-details/:id', component: GroupDetailsComponent },
      { path: 'friund-details/:id', component: FriendDetailsComponent },
      { path: 'profile', component: ProfileComponent },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
