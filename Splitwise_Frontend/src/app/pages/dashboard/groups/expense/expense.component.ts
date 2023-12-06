import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { BackServicesService } from 'src/app/back-services.service';

@Component({
  selector: 'app-expense',
  templateUrl: './expense.component.html',
  styleUrls: ['./expense.component.css']
})
export class ExpenseComponent {
  
  isPSplitChecked: boolean = false;
  isESplitChecked: boolean = false;
  groupId:any;
  members: any[] = [];
  comment: string = '';
  amountPaid: number | undefined;
  payee: any[] = [];
  settlement: any[] = [];

  payeeValues: { [key: number]: number } = {};
  settlementValues: { [key: number]: number } = {};


  constructor(private backService: BackServicesService,) {

  }
  ngOnInit() {
    //to get get the groupId
    console.log('sdfg',this.backService.groupKaId);
    // Set the initial state of the checkbox and split list
    this.addExpenseMeinMembersKaName();
  }

  togglePSplitList() {
    this.isPSplitChecked = !this.isPSplitChecked;
    // This function is triggered whenever the checkbox changes
    // It toggles the value of isSplitChecked which controls the visibility of the div
  }
  toggleESplitList() {
    this.isESplitChecked = !this.isESplitChecked;
    // This function is triggered whenever the checkbox changes
    // It toggles the value of isSplitChecked which controls the visibility of the div
  }

  onSubmit(form: NgForm) {
    if (form.valid) {
      const comment = form.value.comment;
      const amountPaid = form.value.amount;

      const payeeArray = [];
      const settlementArray = [];

      // Loop through members to access payeeValues and settlementValues
      for (let member of this.members) {
        const payeeValue = this.payeeValues[member.id];
        const settlementValue = this.settlementValues[member.id];

        if (payeeValue) {
          payeeArray.push({ userId: member.id, amount: payeeValue });
        }

        if (settlementValue) {
          settlementArray.push({ userId: member.id, amount: settlementValue });
        }
      }

      const payload = {
        groupId: this.backService.groupKaId, // Change groupId as needed
        amountPaid,
        comment,
        payee: payeeArray,
        settlement: settlementArray
      };

      console.log(payload);
      // Here, you can perform further operations with the payload data, like sending it to an API endpoint

      this.backService.addExpense(payload).subscribe(
        (response) => {
          // console.log(this.groupId);
          console.log(response, 'gdxfcgyhuijkoihugyxfcgvhb');
        },
        (error) => {
          console.error('Error adding expense:', error);
        }
      );
      
    }
  }
  



  addExpense(){
    
  }

  addExpenseMeinMembersKaName(){
    this.backService.showGroupKeMembers(this.backService.groupKaId).subscribe(
      (response) => {
        // console.log(this.groupId);
        console.log(response, 'gdxfcgyhuijkoihugyxfcgvhb');
        this.members = response;
        console.log('sadfgds',this.members);
      },
      (error) => {
        console.error('Error fetching group members:', error);
      }
    );
  }

  
}
