import { Component, OnInit } from '@angular/core';
import { BackServicesService } from 'src/app/back-services.service';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.css']
})
export class GroupsComponent implements OnInit{
  groups!: any[];
  id : any = localStorage.getItem('id');
  constructor(
    private backService: BackServicesService
  ) {};

  ngOnInit(): void {
    // this.addGroup();
    this.getGroups();
  }

  getGroups(): void {
    this.backService.groupsOfUser(this.id).subscribe(
      (response) => {
        console.log(this.id);
        this.groups = response;
        console.log(this.groups);
      },
      (error) => {
        console.error('Error fetching groups:', error);
      }
    );
  }

  // addGroup(): void{
  //   this.backService.
  // }



}
