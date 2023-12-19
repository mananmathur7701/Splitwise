import { Component, OnInit, ViewChild } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
//mport { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BackServicesService } from 'src/app/back-services.service';
import Swal from 'sweetalert2';
import { DashboardComponent } from '../dashboard.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  @ViewChild('passwordForm') passwordForm!: NgForm; // ViewChild to access the form
  @ViewChild('myForm') myForm!: NgForm;
  @ViewChild('deleteForm') deleteForm!: NgForm;


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

  // passwordForm: FormGroup;




  constructor(
    private backService: BackServicesService,
    private route: ActivatedRoute,
    private router: Router, 
    private backserviceService: BackServicesService,
    private formBuilder: FormBuilder,
    private dash: DashboardComponent
  ){
    
    
 
  }


  ngOnInit(): void{
    this.getUserDetails();
  }

  toggleEditModal() {
    this.showEditModal = !this.showEditModal;
    // if (!this.showEditModal) {
      this.myForm.resetForm();
    // }
  }

  togglePasswordModal() {
   
    console.log('helo');
    this.showPasswordModal = !this.showPasswordModal;
    
      this.passwordForm.reset();
    
  }

  toggleDeleteModal() {
    console.log('deletion in progress');
    this.showDeleteModal = !this.showDeleteModal;
    // if (!this.showDeleteModal) {
      this.deleteForm.resetForm();
    // }
  }
  

  getUserDetails(): void {
    console.log('id:- ', this.id);
    
    this.backService.showUserDetails(this.id).subscribe(
      (response) => {
        console.log(response);
        this.details = response;
        console.log(this.details);
  
        // Set initial values for the edit user details modal
        this.profileData.firstName = this.details.firstName;
        console.log(this.profileData.firstName)
        this.profileData.lastName = this.details.lastName;
        this.profileData.number = this.details.number;
      },
      (error) => {
        console.error("error fetching user details ", error);
      }
    );
  }

  editUserDetails(): void{
    this.backService.editProfile(this.id, this.profileData.firstName, this.profileData.lastName,this.details.email, this.profileData.number).subscribe(
      (response)=>{
        console.log(response);
        this.toggleEditModal();
        this.getUserDetails();
        this.dash.getUserDetails();
      },
      (error)=>{
        console.error("error editing user details ", error);
      }
    );
  }

 

  // // Custom validator function for password matching
  // passwordMatchValidator(control: AbstractControl) {
  //   const password = control.get('password');
  //   const rePassword = control.get('rePassword');

  //   if (password && rePassword && password.value !== rePassword.value) {
  //     rePassword.setErrors({ passwordMismatch: true });
  //   } else {
  //     rePassword.setErrors(null);
  //   }

  //   return null;
  // }

  editPassword(): void{
    console.log('sadcwsasacascasc',this.profileData);
    if (this.profileData.password== this.profileData.rePassword){
    this.backService.editPassword(this.id,this.profileData.oldPassword,this.profileData.password).subscribe(
      (response) =>{
        console.log(response);
      },
      (error)=>{
        Swal.fire({
          icon: "error",
          title: "OOOPS...",
          text: "Account Password does not match",
          // footer: "Please Settleup All Your Expenses And Try Again..."
        });
        console.error("error changing password ", error);
      }
    );
    }
    else{
      Swal.fire({
        icon: "error",
        title: "OOOPS...",
        text: "New Passwords does not match",
        // footer: "Please Settleup All Your Expenses And Try Again..."
      });
    }
  }

  deleteUser(): void{
    console.log(this.profileData.password);
    

    this.backService.deleteProfile(this.id,this.profileData.password).subscribe(
      (response) =>{
        console.log(response);
      },
      (error)=>{
        console.error("error deleting user ", error);
        // window.alert("Failed to delete profile. Please settleup all your expenses and try again later.");
        Swal.fire({
          icon: "error",
          title: "OOOPS...",
          text: "Please Settleup All Your Expenses And Try Again...",
          // footer: "Please Settleup All Your Expenses And Try Again..."
        });
      }
    );
  }
  
}


