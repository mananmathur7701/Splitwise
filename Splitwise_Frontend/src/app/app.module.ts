import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './pages/register/register.component';
import { LoginComponent } from './pages/login/login.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { RouterModule } from '@angular/router';
import { MatToolbarModule } from '@angular/material/toolbar';
import { LandingComponent } from './landing/landing.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { OtpVerifyComponent } from './otp-verify/otp-verify.component';

import { ExpenseComponent } from './pages/dashboard/groups/expense/expense.component';
import { FriendsComponent } from './pages/dashboard/friends/friends.component';
import { GroupsComponent } from './pages/dashboard/groups/groups.component';
import { HomeComponent } from './pages/dashboard/home/home.component';
import { AddGroupComponent } from './pages/dashboard/add-group/add-group.component';
import { GroupDetailsComponent } from './pages/dashboard/group-details/group-details.component';
import { ProfileComponent } from './pages/dashboard/profile/profile.component';


@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    DashboardComponent,
    FooterComponent,
    HeaderComponent,
    LandingComponent,
    OtpVerifyComponent,
    HomeComponent,
    ExpenseComponent,
    FriendsComponent,
    GroupsComponent,
    AddGroupComponent,
    GroupDetailsComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    MatToolbarModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
