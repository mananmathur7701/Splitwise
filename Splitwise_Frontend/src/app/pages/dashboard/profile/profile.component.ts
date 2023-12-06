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
  myForm!: FormGroup;
  errors!: Error;
  id: any = localStorage.getItem('id');
  newPassword: any;
  password: any;
  details: any = [];
  showEditModal = false;
  showPasswordModal = false;



  constructor(
    private backService: BackServicesService,
    private route: ActivatedRoute,
    private router: Router, 
    private backserviceService: BackServicesService,
    private formBuilder: FormBuilder
  ){
    this.myForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      number: ['', Validators.required],
    });
    
 
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

  editUserDetails(id: number,Form:FormGroup): void{
    this.backService.editProfile(id,Form.value.firstName,Form.value.lastName,Form.value.number,Form.value.email).subscribe(
      (response)=>{
        console.log(response);
        
      },
      (error)=>{
        console.error("error editing user details ", error);
      }
    );
  }

  editPassword(): void{

    this.backService.editPassword(this.id,this.newPassword).subscribe(
      (response) =>{
        console.log(response);
      },
      (error)=>{
        console.error("error changing password ", error);
      }
    );
  }

  deleteUser(): void{

    this.backService.deleteProfile(this.id,this.password).subscribe(
      (response) =>{
        console.log(response);
      },
      (error)=>{
        console.error("error frtching user details ", error);
      }
    );
  }
  
}


