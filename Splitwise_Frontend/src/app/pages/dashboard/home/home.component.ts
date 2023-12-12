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
  ){
    console.log('---------------------------------------------------------------------');
    
    const element1 = {
      amount: 300,
      expId: 1,
      expenseName: "DINNER",
      groupName: "hum 3",
      id: 1,
      userId: 1
    };
    
    const element2 = {
      amount: 2000,
      expenseId: 2,
      expenseName: "lunch",
      groupId: 10,
      groupName: "hum 3",
      id: 3,
      payeeId: 52,
      payeeMail: "rsoni@gmail.com",
      payerId: 1,
      payerMail: "akumar@argusoft.com"
    };
    
    // Differentiating based on keys' existence
    const keysElement1 = Object.keys(element1);
    const keysElement2 = Object.keys(element2);
    
    if (keysElement1.length !== keysElement2.length) {
      console.log('The elements have different keys.');
    } else {
      console.log('The elements have the same number of keys.');
    }
    
    console.log('---------------------------------------------------------------------');
    
  };


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
      console.log('before sort: ',response.payments.concat(response.expenses));
      this.transactionList.sort((a, b) => {
        const expenseIdA = a.expenseId || 0; // If expenseId is undefined, set it as 0
        const expenseIdB = b.expenseId || 0;
  
        return expenseIdA - expenseIdB; // Sorting based on expenseId
      });
      console.log('after sort: ',this.transactionList);
    },
    (error) => {
      console.error('Error fetching payments and expenses:', error);
    }
  );
}

getColor(element: any): string {
  // console.log('this is the element',element);
  
  const element1 = {
    amount: 300,
    expenseId: 1,
    expenseName: "DINNER",
    groupName: "hum 3",
    id: 1,
    userId: 1
  };
  const defaultKey = Object.keys(element1);
  const keysElement = Object.keys(element);
  // console.log('this is the length ',keysElement.length,'  this is the default lenght - ',defaultKey.length);
  

  // Checking the condition of different lengths of keys
  if (keysElement.length != defaultKey.length) {
    // If lengths differ, return red
    // console.log('this is the colour given :- RED');
    
    return 'red';
  } else {
    // console.log('this is the colour given :- GREEN');
    // If lengths are the same, return green
    return '#00ff19';
  }
}

}
