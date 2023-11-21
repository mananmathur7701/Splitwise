import { Component, OnInit } from '@angular/core';
import { BackServicesService } from 'src/app/back-services.service';

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.css']
})
export class FriendsComponent implements OnInit {
  friends!: any[];
  id : any = localStorage.getItem("id");
  mylist!:any[];
  owed:number |any;
  owes:number |any;
  balance:number |any;

  constructor(
    private backService: BackServicesService
  ){};


  ngOnInit(): void {
    this.udhariKaData();
    //throw new Error('Method not implemented.');
  }
  


  // getLeneKaSum():void{
  //   this.backService.sumOfLeneKaMoney().subscribe(
  //     (response) => {
  //       console.log();
  //       this.friends = response;
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
  //       this.friends = response;
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
        this.friends=response;
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
