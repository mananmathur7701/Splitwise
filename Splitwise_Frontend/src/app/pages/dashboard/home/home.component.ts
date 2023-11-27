import { Component, OnInit } from '@angular/core';
import { BackServicesService } from 'src/app/back-services.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit
{
  home!: any[];
  mylist!:any[];
  id : any = localStorage.getItem("id");
  owed:number | any;
  owes:number |any;
  balance:number |any;
  constructor(private backService: BackServicesService){};

  ngOnInit(): void
  {
    // this.getHome();
    this.udhariKaData();
  }

  // getHome(): void
  // {
  //   this.backService.expensesOfUserWhereOwed(this.id).subscribe(
  //     (response) => {
  //       console.log(this.id);
  //       this.home= response;
  //       console.log(this.home);

  //       this.backService.expensesOfUserWhereOwes(this.id).subscribe(
  //         (response) => {
  //           console.log(this.id);
  //           this.home=response;
  //           console.log(this.home);
  //         }
  //       )
  //     },
  //     (error) => {
  //       console.error("Error fetching details:", error);
  //     }
  //   );
  // }

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

  // getLeneKaSum():void{
  //   this.backService.sumOfLeneKaMoney().subscribe(
  //     (response) => {
  //       console.log();
  //       this.home = response;
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
  //       this.home = response;
  //       console.log();
  //     },
  //     (error) => {
  //       console.error("error fetching data", error);
  //     }
  //   );
  // }

  // lenaDenaBalance(): void{
  //   this.backService.lenaDenaBalance().subscribe(
  //     (response) => {
  //       console.log();
  //       this.home=response;
  //       console.log();
  //     },
  //     (error) => {
  //       console.error("error fetching data", error);
  //     }
  //   );
  // }
}
