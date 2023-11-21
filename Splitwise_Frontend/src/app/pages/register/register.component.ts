import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { BackServicesService } from 'src/app/back-services.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  myForm!: FormGroup;
  errors!: Error;

//----------------------------------Constructor--------------------
  constructor(private router: Router, private backserviceService: BackServicesService,private formBuilder: FormBuilder)
  {
    this.myForm = this.formBuilder.group({
          firstName: ['', Validators.required],
          lastName: ['', Validators.required],
          email: ['', [Validators.required, Validators.email]],
          number: ['', Validators.required],
          password: ['', Validators.required],
          confirmPassword: ['', Validators.required],
        });
    console.log("Sign Up Is Rendered")
  }

//-----------------------------------ngOnInit-----------------------
  ngOnInit():void{
    // this.myForm=new FormGroup(
    //   {
    //       email:new FormControl('',Validators.required),
    //       firstName:new FormControl('',Validators.required),
    //       lastName:new FormControl('',Validators.required),
    //       number:new FormControl('',Validators.required),
    //       password:new FormControl('',Validators.required),
    //       confirmPassword:new FormControl('',Validators.required),

    //   }
    // )
  }

  // toRegister(Form: FormGroup)
  // {
  //   this.backserviceService.signup(Form.value.firstName,Form.value.lastName,Form.value.email,Form.value.number,Form.value.password).subscribe(
  //     (data)=>{
  //       console.log(data);
  //     },
  //     (error)=>{
  //       console.log(error);
  //     }
  //   );
  // }

  toRegister(Form: FormGroup) {
    return new Promise((resolve, reject) => 
    {
        this.backserviceService.signup(Form.value.firstName, Form.value.lastName, Form.value.email, Form.value.number, Form.value.password).subscribe
        (
          (data) => {
            console.log(data);
            this.router.navigateByUrl('/otp');
            resolve(data); // Resolve the Promise with the data
          },
          (error) => {
            console.log(error);
            this.myForm.reset();
            this.errors=error;
            reject(error); // Reject the Promise with the error
          }
        );
    });
  }

  proceed()
  {
    if(this.errors)
    {
      this.myForm.reset();
    }
    else
    {
      this.router.navigateByUrl('/otp');
    }
  }

  logIn()
  {
    console.log("wprk");
    this.router.navigateByUrl('/login');
  }

  isFormValid():boolean
  {
    return this.myForm.valid;
  }
}

