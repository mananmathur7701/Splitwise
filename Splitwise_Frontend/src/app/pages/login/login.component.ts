// import { Component } from '@angular/core';

// @Component({
//   selector: 'app-login',
//   templateUrl: './login.component.html',
//   styleUrls: ['./login.component.css']
// })
// export class LoginComponent {

// }




import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BackServicesService } from 'src/app/back-services.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm!: FormGroup;

//----------------------------------Constructor--------------------
  constructor(private router: Router, private backserviceService: BackServicesService)
  {
    console.log("Sign Up Is Rendered")
  }

//-----------------------------------ngOnInit-----------------------
  ngOnInit():void{
    this.loginForm=new FormGroup(
      {
          email:new FormControl('',Validators.required),
          password:new FormControl('',Validators.required),
      }
    )
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

  toLogin(Form: FormGroup) {
    return new Promise((resolve, reject) => 
    {
        this.backserviceService.login(Form.value.email,Form.value.password).subscribe
        (
          (data) => {
            console.log(data);
            resolve(data); // Resolve the Promise with the data
          },
          (error) => {
            console.log(error);
            reject(error); // Reject the Promise with the error
          }
        );
    });
  }

  isFormValid():boolean
  {
    return this.loginForm.valid;
  }
}


