import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { BackServicesService } from '../back-services.service';

@Component({
  selector: 'app-otp-verify',
  templateUrl: './otp-verify.component.html',
  styleUrls: ['./otp-verify.component.css']
})
export class OtpVerifyComponent
{
  otpForm: FormGroup;
  // otp: string = '';
  errors: Error | undefined;

  constructor(private router: Router, private backserviceService: BackServicesService, private formBuilder: FormBuilder) {
    this.otpForm = this.formBuilder.group({
      otp: new FormControl('', Validators.required), // Add 'otp' control here
    });
    console.log("OTP Verify Component is Rendered");
  }

  otpVerify() {
    console.log(this.otpForm.value.otp);
    console.log("chal ja ")
    return new Promise((resolve, reject) => {
      console.log("ts file 1");
      this.backserviceService.otpverify(this.otpForm.value.otp).subscribe(
        (data:any) => {
          console.log(data);
          const id = data.id;
          //localStorage.setItem("id",String(id));
          localStorage.setItem("id",id);
          this.router.navigateByUrl('/dashboard/home');
          //this.router.navigateByUrl('/dashboard');
          resolve(data);
        },
        (error) => {
          console.log(error);
          this.errors = error;
          this.router.navigateByUrl('/register');
          reject(error);
        }
      );
    });
  }

  verify() {
    if (this.errors) {
      this.router.navigateByUrl('/register');
    } else {
      this.router.navigateByUrl('/login');
    }
  }

  isFormValid(): boolean {
    return this.otpForm.valid;
  }
}