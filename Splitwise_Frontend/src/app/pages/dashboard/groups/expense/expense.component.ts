import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-expense',
  templateUrl: './expense.component.html',
  styleUrls: ['./expense.component.css']
})
export class ExpenseComponent {
  isSplitChecked: boolean = false;

  ngOnInit() {
    // Set the initial state of the checkbox and split list
    this.isSplitChecked = false;
    this.toggleSplitList(); // Call to initially hide the list
  }

  toggleSplitList() {
    // This function is triggered whenever the checkbox changes
    // It toggles the value of isSplitChecked which controls the visibility of the div
  }

  onSubmit(form: NgForm) {
    // Handle form submission logic here
  }
}
