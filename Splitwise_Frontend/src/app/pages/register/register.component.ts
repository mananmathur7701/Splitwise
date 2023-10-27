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

//----------------------------------Constructor--------------------
  constructor(private router: Router, private backserviceService: BackServicesService)
  {
    console.log("Sign Up Is Rendered")
  }

//-----------------------------------ngOnInit-----------------------
  ngOnInit():void{
    this.myForm=new FormGroup(
      {
          email:new FormControl('',Validators.required),
          firstName:new FormControl('',Validators.required),
          lastName:new FormControl('',Validators.required),
          number:new FormControl('',Validators.required),
          password:new FormControl('',Validators.required),
          confirmPassword:new FormControl('',Validators.required),

      }
    )
  }

  toRegister(Form: FormGroup)
  {
    this.backserviceService.signup(Form.value.firstName,Form.value.lastName,Form.value.email,Form.value.number,Form.value.password).subscribe(
      // (data:{[x:string]:string;})=>{
      //   if (data["message"] == "user added")
      //   {
      //     this.router.navigateByUrl('/signin');
      //   }
      //   else
      //   {
      //     console.log("user already existed");
      //   }
      (data)=>{
        console.log(data);
      },
      (error)=>{
        console.log(error);
      }
    );
  }

  isFormValid():boolean
  {
    return this.myForm.valid;
  }
}

