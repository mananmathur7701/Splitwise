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
  mylist!:any[];
  owed:number |any;
  owes:number |any;
  balance:number |any;
  constructor(
    private backService: BackServicesService
  ) {};

  ngOnInit(): void {
    // this.addGroup();
    this.getGroups();
  //  this.addGroup();
    this.udhariKaData();
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
  //   this.backService.addNewGroup(this.id, this.groupName).subscribe(
  //     (response) =>{
  //       console.log(this.id);
  //       this.groups=response;
  //       console.log(this.groups);
  //     },
  //     (error: any) => {
  //       console.error('Error fetching groups:', error);
  //     }
  //   );
  // }
  // groupName(id: any, groupName: any) {
  //   throw new Error('Method not implemented.');
  // }


  // getLeneKaSum():void{
  //   this.backService.sumOfLeneKaMoney().subscribe(
  //     (response) => {
  //       console.log();
  //       this.groups = response;
  //       console.log();
  //     },
  //     (error) => {
  //       console.error("error fetching data", error);
  //     }
  //   );
  // }

  // getDeneKaSum(): void{
  //   this.backService.sumOfDeneKaMoney().subscribe(
  //     (response) => {
  //       console.log();
  //       this.groups = response;
  //       console.log();
  //     },
  //     (error) => {
  //       console.error("error fetching data", error);
  //     }
  //   );
  // }

  lenaDenaBalance(): void{
    this.backService.lenaDenaBalance(this.id).subscribe(
      (response) => {
        console.log();
        this.groups=response;
        console.log();
      },
      (error) => {
        console.error("error fetching data", error);
      }
    );
  }


  udhariKaData(){
    this.backService.dashboardKaData(this.id).subscribe(
      (data)=>{
        console.log(data);
        this.mylist=data;
        this.owed=this.mylist[0];
        this.owes=this.mylist[1];
        this.balance=this.mylist[2];
      },
      (error)=>
      {
        console.log(error);
      }
    )
  }

}
