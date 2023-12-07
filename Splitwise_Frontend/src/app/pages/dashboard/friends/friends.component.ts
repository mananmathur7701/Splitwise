import { Component, OnInit } from '@angular/core';
import { BackServicesService } from 'src/app/back-services.service';

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.css']
})
export class FriendsComponent implements OnInit {

  friends!: any[];
  userId : any = localStorage.getItem("id");
  mylist!:any[];
  owed:any;
  owes:any;
  balance:number |any;
  dosts: any[] = [];
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
    this.getUserKeFriends();
    //throw new Error('Method not implemented.');
  }
  

  onSubmit() {
    this.addNewSquareOffTransaction(this.settleToBeAmt);
    }

  toggleModal(friendId:any,debtAmt:any) {
      this.payeeid = friendId;
      this.friendAmt = debtAmt;
    this.showModal = !this.showModal;
  }

  closeModalHandler() {
    this.showModal = false; // Close the modal
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
    this.backService.lenaDenaBalance(this.userId).subscribe(
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

  getUserKeFriends(): void{
    this.backService.showUserKeFriends(this.userId).subscribe(
      (response) => {
        // console.log(this.groupId);
        console.log(response, 'gdxfcgyhuijkoihugyxfcgvhb');
        this.dosts = response;
        console.log('sadfgds',this.dosts);
      },
      (error) => {
        console.error('Error fetching friends:', error);
      }
    );
  }


  addNewSquareOffTransaction(amount:any): void{
    console.log('the amount needed to be settle: ' ,amount);
    
    this.backService.addNewSquareOffTransaction(this.payeeid,this.userId,amount).subscribe(
      (response) => {
        // console.log(this.groupId);
        console.log(response, 'gdxfcgyhuijkoihugyxfcgvhb');
        // this.dosts = response;
        // console.log('sadfgds',this.dosts);
      },
      (error) => {
        console.error('Error fetching friends:', error);
      }
    );
  }




}
