import { Component } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  user: any = {};

  onSubmit() {
    // Handle form submission here (e.g., send data to a server)
    console.log('User submitted:', this.user);
  }
}

