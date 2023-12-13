import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { BackServicesService } from 'src/app/back-services.service';
import { Router } from '@angular/router';


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
  


  constructor(
    private backService: BackServicesService,
    private router: Router
    ) {

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

      let totalPayeeAmount = 0; // Total amount in "WHO PAID HOW MUCH" section
      let totalSettlementAmount = 0; //
      console.log(form.value);
      

      // Loop through members to access payeeValues and settlementValues
      for (let member of this.members) {
        const payeeValue = this.payeeValues[member.id];
        const settlementValue = this.settlementValues[member.id];

        if (payeeValue) {
          payeeArray.push({ userId: member.id, amount: payeeValue });
          totalPayeeAmount += payeeValue;
        }
        if(!this.isESplitChecked){
          console.log('inside');
          
          settlementArray.push({ userId: member.id, amount: (amountPaid/this.members.length) });
          totalSettlementAmount += (amountPaid/this.members.length); 
        }
        if (settlementValue) {
          settlementArray.push({ userId: member.id, amount: settlementValue });
          totalSettlementAmount += settlementValue; 
        }
      }
      console.log('payeeArray',payeeArray);
      
      console.log('settelment array',settlementArray);
      

      // Validate if the total amounts match the amount paid
    if (totalPayeeAmount !== amountPaid || totalSettlementAmount !== amountPaid) {
      // Handle validation error (amounts do not match)
      // For example, you can show an error message or prevent form submission
      console.error('Total amounts do not match the amount paid.');
      alert('The total amounts do not match the amount paid/ amount to be paid.');
      return; // Prevent further execution
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
          this.router.navigate(['/dashboard/group-details/'+ this.backService.groupKaId]);
        },
        (error) => {
          console.error('Error adding expense:', error);
        }
      );
      
    }
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
