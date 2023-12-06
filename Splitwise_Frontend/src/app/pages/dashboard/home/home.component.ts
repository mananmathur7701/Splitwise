import { Component, OnInit } from '@angular/core';
import { BackServicesService } from 'src/app/back-services.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit
{
  friends!: any[];
  id : any = localStorage.getItem("id");
  mylist!:any[];
  owed:number |any;
  owes:number |any;
  balance:number |any;
  dosts: any[] = [];
  paymentsList: any[] = [];
  
  constructor(
    private backService: BackServicesService
  ){};


  ngOnInit(): void {
    this.udhariKaData();
    this.getPaymentsDoneByUser();
    // this.getUserKeFriends();
    //throw new Error('Method not implemented.');
  }
  



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

  // getUserKaExpenses(): void{
  //   this.backService.showUserKaExpenses(this.id).subscribe(
  //     (response) => {
  //       // console.log(this.groupId);
  //       console.log(response, 'gdxfcgyhuijkoihugyxfcgvhb');
  //       this.dosts = response;
  //       console.log('sadfgds',this.dosts);
  //     },
  //     (error) => {
  //       console.error('Error fetching friends:', error);
  //     }
  //   );
  // }


  getPaymentsDoneByUser(): void{
    this.backService.listOfPayementsDoneByUser(this.id).subscribe(
      (response) => {
        console.log(this.id);
        console.log(response);
        this.paymentsList = response;
        console.log(this.paymentsList);        
      },
      (error) => {
        console.error('Error fetching payments list:', error);
      }
    );
  }

}
