import { Component, OnInit } from '@angular/core';
import { BackServicesService } from 'src/app/back-services.service';
@Component({
  selector: 'app-group-details',
  templateUrl: './group-details.component.html',
  styleUrls: ['./group-details.component.css']
})
export class GroupDetailsComponent implements OnInit{
  
    groups!: any[];
    id : any = localStorage.getItem('id');
    constructor(
      private backService: BackServicesService
    ) {};
  
    ngOnInit(): void {
      // this.addGroup();
      this.getGroupDetails();
      
    }
  getGroupDetails(): void {
    this.backService.showAllGroupExpense(this.id).subscribe(
      (response) => {
        console.log(this.id);
        this.getGroupDetails = response;
        console.log(this.getGroupDetails);
      },
      (error) => {
        console.error("Error fetching details:", error);
      }
    );
    
  }
  


}
