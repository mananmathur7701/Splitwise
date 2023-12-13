import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
//mport { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BackServicesService } from 'src/app/back-services.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  profileData: any = {
    firstName: '',
    lastName: '',
    number: '',
  };
  errors!: Error;
  id: any = localStorage.getItem('id');
  newPassword: any;
  password: any;
  details: any = [];
  showEditModal = false;
  showPasswordModal = false;
  showDeleteModal = false;



  constructor(
    private backService: BackServicesService,
    private route: ActivatedRoute,
    private router: Router, 
    private backserviceService: BackServicesService,
    private formBuilder: FormBuilder
  ){
    
    
 
  }


  ngOnInit(): void{
    this.getUserDetails();
  }

  toggleEditModal() {
    this.showEditModal = !this.showEditModal;
  }

  togglePasswordModal() {
    console.log('helo');
    this.showPasswordModal = !this.showPasswordModal;
  }

  toggleDeleteModal() {
    console.log('deletion in progress');
    this.showDeleteModal = !this.showDeleteModal;
  }
  

  getUserDetails(): void{
    console.log('id:- ', this.id);
    
    this.backService.showUserDetails(this.id).subscribe(
      (response) =>{
        console.log(response);
        this.details = response;
        console.log(this.details);
      },
      (error)=>{
        console.error("error frtching user details ", error);
      }
    );
      
  }

  editUserDetails(): void{
    this.backService.editProfile(this.id, this.profileData.firstName, this.profileData.lastName,this.details.email, this.profileData.number).subscribe(
      (response)=>{
        console.log(response);
        this.router.navigate(['/dashboard/home']);
        
      },
      (error)=>{
        console.error("error editing user details ", error);
      }
    );
  }

  editPassword(): void{
    console.log('sadcwsasacascasc',this.profileData);
    

    this.backService.editPassword(this.id,this.profileData.oldPassword,this.profileData.password).subscribe(
      (response) =>{
        console.log(response);
      },
      (error)=>{
        console.error("error changing password ", error);
      }
    );
  }

  deleteUser(): void{
    console.log(this.profileData.password);
    

    this.backService.deleteProfile(this.id,this.profileData.password).subscribe(
      (response) =>{
        console.log(response);
      },
      (error)=>{
        console.error("error frtching user details ", error);
      }
    );
  }
  
}


