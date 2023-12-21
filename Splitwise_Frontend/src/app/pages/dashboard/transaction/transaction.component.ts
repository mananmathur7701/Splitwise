import { Component } from '@angular/core';
import { BackServicesService } from 'src/app/back-services.service';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent {

     friends!: any[];
    userId : any = localStorage.getItem("id");
    mylist!:any[];
    owed:any;
    owes:any;
    balance:number |any;
    transactions: any[] = [];
    showModal = false;
    amount : any;
    payeeid : any;
    friendAmt:any;
    settleToBeAmt : any;
    constructor(
      private backService: BackServicesService
    ){};
  
  
    ngOnInit(): void {
      this.udhariKaData();
      
      //throw new Error('Method not implemented.');
      this.getTransactionList();
    }
    
  
    udhariKaData(){
      this.backService.dashboardKaData(this.userId).subscribe(
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
  
    getColor(element: any): string {
      // console.log('this is the element',element);
    
      // Checking the condition of different lengths of keys
      if (element == this.userId) {
        // If lengths differ, return red
        // console.log('this is the colour given :- RED');
        
        return '#00ff19';
      } else {
        // console.log('this is the colour given :- GREEN');
        // If lengths are the same, return green
        return '#df4e4e';
      }
    }
  
    getTransactionList(): void{
      this.backService.listOfSquareOffTransactionOfUser(this.userId).subscribe(
        (response) => {
          // console.log(this.groupId);
          console.log(response, 'transaction list ka data');
          this.transactions = response;
          console.log('transaction',this.transactions);
          
        },
        (error) => {
          console.error('Error fetching transactions', error);
        }
      );
    }
  
  
  
  }


