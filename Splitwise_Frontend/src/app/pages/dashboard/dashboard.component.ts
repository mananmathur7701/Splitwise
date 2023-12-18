import { Component } from '@angular/core';
import { NavigationEnd, RouterLinkActive } from '@angular/router';
import { BackServicesService } from 'src/app/back-services.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  [x: string]: any;
  

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
    localStorage.removeItem('id');
    localStorage.removeItem('token');
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
    this['router'].events.subscribe((event: any) => {
      if (event instanceof NavigationEnd) {
        this.index = 0; // Reset the index for typewriter effect
        this.displayedFirstName = ''; // Reset the displayedFirstName
        this.typeWriterFirstName(); // Call your typewriter function
      }
    });
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

  const typeEffect = () => {
    const interval = setInterval(() => {
      if (this.index < helloString.length) {
        this.displayedFirstName += helloString.charAt(this.index);
        this.index++;
      } else {
        clearInterval(interval); // Stop the typewriter effect when finished
        // Reset values to restart the typewriter effect
        this.index = 0;
        this.displayedFirstName = '';
        // Call the function again to start over
        typeEffect();
      }
    }, 300);
  };

  // Start the typewriter effect
  typeEffect();
}

}


    

