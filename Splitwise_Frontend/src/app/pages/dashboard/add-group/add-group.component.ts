import { Component, Output, EventEmitter, ViewChild } from '@angular/core';
import { RouterLink } from '@angular/router';
import { BackServicesService } from 'src/app/back-services.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-add-group',
  templateUrl: './add-group.component.html',
  styleUrls: ['./add-group.component.css']
})
export class AddGroupComponent {
  @ViewChild('myForm') myForm!: NgForm; // ViewChild to access the form

  groupData: any = { groupName: '' }; // Define groupData object with groupName property
  id = localStorage.getItem("id");
  groups: any;

  constructor(private backService: BackServicesService, private router: Router) {}


  onSubmit(): void {
    this.addGroup(this.groupData.groupName);
    
  }

  addGroup(groupName: string): void {
    this.backService.addNewGroup(String(this.id), groupName).subscribe(
      (response: any) => {
        console.log(this.id);
        this.groups = response;
        console.log(this.groups);
        this.router.navigate(['/dashboard/group-details',this.groups.id]);
        // You might want to emit an event here if needed.
      },
      (error: any) => {
        console.error('Error adding group:', error);
      }
    );
  }

  @Output() closeModal: EventEmitter<void> = new EventEmitter<void>();

  onCloseClick(): void {
    this.closeModal.emit(); // Emit an event when the close button is clicked
    this.myForm.resetForm(); // This resets the form
  }
}
