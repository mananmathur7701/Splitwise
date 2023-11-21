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

  constructor(
    private backService: BackServicesService
  ){};


  ngOnInit(): void {
    
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
}
