import { Component, OnInit } from '@angular/core';
import { BackServicesService } from 'src/app/back-services.service';
import { forkJoin } from 'rxjs';

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
  transactionList: any[]=[];
  
  constructor(
    private backService: BackServicesService
  ){};


  ngOnInit(): void {
    this.udhariKaData();
    this.getEntireTransactionDetailsOfUser();
    //this.getPaymentsDoneByUser();
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


  getEntireTransactionDetailsOfUser(): void{
    const listOfPaymentsObs = this.backService.listOfPayementsDoneByUser(this.id);
  const listOfExpensesObs = this.backService.listOfPaymentsDoneForUser(this.id);

  forkJoin({
    payments: listOfPaymentsObs,
    expenses: listOfExpensesObs
  }).subscribe(
    (response) => {
      console.log('Payments:', response.payments);
      console.log('Expenses:', response.expenses);
      // Handle payments and expenses data as needed
      this.transactionList = response.payments.concat(response.expenses);
      console.log('amrit',this.transactionList);
      
    },
    (error) => {
      console.error('Error fetching payments and expenses:', error);
    }
  );
}

}
