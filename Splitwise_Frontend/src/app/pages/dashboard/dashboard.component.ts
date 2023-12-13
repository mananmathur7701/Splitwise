import { Component } from '@angular/core';
import { RouterLinkActive } from '@angular/router';
import { BackServicesService } from 'src/app/back-services.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  

  id: any = localStorage.getItem('id');
  details: any = {
    email : '',
    firstName : '',
    id : '',
    lastName : '', 
    number : ''
  };
  displayedFirstName: string = ''; // To display the first name letter by letter
  index: number = 0; // Index for displaying letters one by one



  logout() {
    localStorage.setItem('id', '');
  }

  constructor(
    private backService: BackServicesService,
    // private route: ActivatedRoute,
    // private router: Router, 
    // private backserviceService: BackServicesService,
    // private formBuilder: FormBuilder
  ){}

  ngOnInit(): void{
    this.getUserDetails();
  }

  getUserDetails(): void{
    console.log('id:- ', this.id);
    
    this.backService.showUserDetails(this.id).subscribe(
      (response) =>{
        console.log(response);
        this.details = response;
        console.log(this.details);
        this.typeWriterFirstName();
      },
      (error)=>{
        console.error("error frtching user details ", error);
      }
    );
      
  }
  typeWriterFirstName(): void {
    const firstName = this.details.firstName || '';
    const helloString = 'Hello ' + firstName;
    const interval = setInterval(() => {
      if (this.index < helloString.length) {
        this.displayedFirstName += helloString.charAt(this.index);
        this.index++;
      } else {
        clearInterval(interval); // Stop the typewriter effect when finished
      }
    }, 300);
  }

}


    

