import { Component, OnInit } from '@angular/core';
import { BackServicesService } from 'src/app/back-services.service';
@Component({
  selector: 'app-group-details',
  templateUrl: './group-details.component.html',
  styleUrls: ['./group-details.component.css'],
})
export class GroupDetailsComponent implements OnInit {
  groups!: any[];
  id: any = localStorage.getItem('id');

  showModal = false;
  members: string[] = []; // Array to store members

  addMember(email: string) {
    if (email.trim() !== '' && this.members.indexOf(email) === -1) {
      this.members.push(email);
    }
  }

  removeMember(email: string) {
    const index = this.members.indexOf(email);
    if (index !== -1) {
      this.members.splice(index, 1);
    }
  }
  toggleModal() {
    this.showModal = !this.showModal;
  }

  closeModalHandler() {
    this.showModal = false; // Close the modal
  }

  constructor(private backService: BackServicesService) {}

  ngOnInit(): void {
    // this.addGroup();
    this.getGroupDetails();
  }
  getGroupDetails(): void {
    this.backService.showAllGroupExpense(this.id).subscribe(
      (response) => {
        console.log(this.id);
        this.getGroupDetails = response;
        console.log(this.getGroupDetails);
      },
      (error) => {
        console.error('Error fetching details:', error);
      }
    );
  }
}
