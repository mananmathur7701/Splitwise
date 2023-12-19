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
  
  isPSplitChecked: boolean = true;
  isESplitChecked: boolean = false;
  isEqualSplitChecked: boolean = true;
  groupId:any;
  members: any[] = [];
  comment: string = '';
  amountPaid: number | undefined;
  payee: any[] = [];
  settlement: any[] = [];

  payeeValues: { [key: number]: number } = {};
  settlementValues: { [key: number]: number } = {};
  expenseDate: any;
  groupName: any;
  splitValues: { [key: number]: boolean} = {};
  


  constructor(
    private backService: BackServicesService,
    private router: Router
    ) {

          // Get the current date and time
    const currentDate = new Date();

    // Format the date to match the datetime-local input format (YYYY-MM-DDTHH:mm)
    const year = currentDate.getFullYear();
    const month = ('0' + (currentDate.getMonth() + 1)).slice(-2); // Months are zero-based
    const day = ('0' + currentDate.getDate()).slice(-2);
    const hours = ('0' + currentDate.getHours()).slice(-2);
    const minutes = ('0' + currentDate.getMinutes()).slice(-2);

    // Assign the formatted date and time to the expenseDate variable
    this.expenseDate = `${year}-${month}-${day}T${hours}:${minutes}`;

  }
  ngOnInit() {
    //to get get the groupId
    console.log('sdfg',this.backService.groupKaId);
    // Set the initial state of the checkbox and split list
    this.addExpenseMeinMembersKaName();
    this.getGroupKaNaam();
    // this.members.forEach(member => {
    //   this.splitValues[member.id] = true; // Set default value to true for each member ID
    // });
  }

  togglePSplitList() {
    this.isPSplitChecked = !this.isPSplitChecked;
    if (this.isPSplitChecked) {
      this.isESplitChecked = false; // Unselect split2 checkbox if split is selected
    }
    // This function is triggered whenever the checkbox changes
    // It toggles the value of isSplitChecked which controls the visibility of the div
  }
  toggleESplitList() {
    this.isESplitChecked = !this.isESplitChecked;
    if (this.isESplitChecked) {
      this.isPSplitChecked = false; 
    }
    // This function is triggered whenever the checkbox changes
    // It toggles the value of isSplitChecked which controls the visibility of the div
  }

  onSubmit(form: NgForm) {
    if (form.valid) {
      const comment = form.value.comment;
      const amountPaid = form.value.amount;
      const spentAt = new Date(form.value.expenseDate );
      if(spentAt>new Date())
      {
        alert("You cannot add future Expense");
        return;
      }

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

        
        // if(this.isESplitChecked){
        //   console.log('inside');
          
        //   settlementArray.push({ userId: member.id, amount: (amountPaid/this.members.length) });
        //   totalSettlementAmount += (amountPaid/this.members.length); 
        // }
        
        if (settlementValue) {
          settlementArray.push({ userId: member.id, amount: settlementValue });
          totalSettlementAmount += settlementValue; 
        }
      }
      if(this.isPSplitChecked){
        totalSettlementAmount = 0;
        settlementArray.forEach(ele=>{
        ele.amount = amountPaid/settlementArray.length
        totalSettlementAmount = totalSettlementAmount+ele.amount;
      });
    }
      console.log('payeeArray',payeeArray);
      
      console.log('settelment array',settlementArray);

      
      console.log('settelment array',settlementArray);
      console.log(totalSettlementAmount,' and ', totalPayeeAmount,' and ',amountPaid);
      
      // Validate if the total amounts match the amount paid
    if (totalPayeeAmount !== amountPaid || Math.round(totalSettlementAmount) !== amountPaid) {
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
        spentAt,
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

  getGroupKaNaam(): void {
    console.log("get group name");
    console.log(this.backService.groupKaId);
    this.backService.showGroupKaName(this.backService.groupKaId).subscribe(
      (response) => {
        
        console.log(response, 'gdxfcgyhuijkoihugyxfcgvhb');
        this.groupName = response;
        console.log(" aja bhai naam", response)
        
        // console.log(this.groupName);
      },
      (error) => {
        console.error('Error fetching group name:', error);
      }
    );
  }

  
}
