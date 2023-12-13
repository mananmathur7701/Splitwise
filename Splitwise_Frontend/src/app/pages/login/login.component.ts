// import { Component } from '@angular/core';

// @Component({
//   selector: 'app-login',
//   templateUrl: './login.component.html',
//   styleUrls: ['./login.component.css']
// })
// export class LoginComponent {

// }




import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { BackServicesService } from 'src/app/back-services.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm!: FormGroup;
  errors:Error | undefined;

//----------------------------------Constructor--------------------
  constructor(private router: Router, private backserviceService: BackServicesService, private formBuilder:FormBuilder)
  {
    this.loginForm = this.formBuilder.group({
      email:new FormControl('',Validators.required),
      password:new FormControl('',Validators.required),
    });
    console.log("Sign Up Is Rendered")
  }

//-----------------------------------ngOnInit-----------------------
  ngOnInit():void{
    // this.loginForm=new FormGroup(
    //   {
    //       email:new FormControl('',Validators.required),
    //       password:new FormControl('',Validators.required),
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

  toLogin(Form: FormGroup) {
    console.log("hello");

    this.backserviceService.login(Form.value.email,Form.value.password).subscribe(
      
      (response:any) => {
        // Handle the response from the backend here
        console.log('Sucessfully Logged In', response);
        this.backserviceService.setToken(response.accessToken);
        // this.login.loginUser(response.accessToken);
        // this.backserviceService.getCurrentUser().subscribe(
        //   (user:any)=>{
            
        //     console.log(user);
            // if(user!= null){
              this.router.navigateByUrl('/dashboard');
              // this.login.loginStatusSubject.next(true);
      //       }
           
      //     }
      //   )
      },
      error => {
        // Handle any errors here
        console.error('Error:', error);
      
      }
    );
    
    // return new Promise((resolve, reject) => 
    // {
    //     this.backserviceService.login(Form.value.email,Form.value.password).subscribe
    //     (
    //       (data) => {
    //         console.log('token :',data);
    //         this.backserviceService.getCurrentUser().subscribe();
    //         const id = data.id;
    //         //localStorage.setItem("id",String(id));
    //         localStorage.setItem("id",id);
    //         this.router.navigateByUrl('/dashboard/home');
    //         resolve(data); // Resolve the Promise with the data
    //       },
    //       (error) => {
    //         console.log(error);
    //         this.loginForm.reset();
    //         alert("Wrong Passwprd")
    //         reject(error); // Reject the Promise with the error
    //       }
    //     );
    // });
  }

  proceed()
  {
    if(this.errors)
    {
      this.loginForm.reset();
    }
    else
    {
      this.router.navigateByUrl('/otp');
    }
  }

  signUp()
  {
    this.router.navigateByUrl('/register');
  }

  isFormValid():boolean
  {
    return this.loginForm.valid;
  }
}


