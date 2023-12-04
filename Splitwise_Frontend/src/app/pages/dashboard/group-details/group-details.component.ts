import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BackServicesService } from 'src/app/back-services.service';

@Component({
  selector: 'app-group-details',
  templateUrl: './group-details.component.html',
  styleUrls: ['./group-details.component.css'],
})
export class GroupDetailsComponent implements OnInit {
  groups!: any[];
  // id: any = localStorage.getItem('id');
  groupId: number = 0;
  showModal = false;
  members: any[] = []; // Array to store members
  membersToAdd: any[] = []; // Array to store the members to be added
  groupName: any;
  expensesList: any[] = [];
  router: any;


  


  addMember(email: string) {
    if (email.trim() !== '' && this.membersToAdd.indexOf(email) === -1) {
      this.membersToAdd.push(email);
    }
  }



  removeMember(email: string) {
    const index = this.membersToAdd.indexOf(email);
    if (index !== -1) {
      this.membersToAdd.splice(index, 1);
    }
  }
  toggleModal() {
    this.showModal = !this.showModal;
  }

  closeModalHandler() {
    this.showModal = false; // Close the modal
  }
  constructor(
    private backService: BackServicesService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // this.addGroup();
    this.route.params.subscribe((params) => {
      // Access the 'id' parameter from the URL
      this.groupId = params['id'];
      // You can now use this.groupId in this component
      console.log('Group ID from URL parameter:', this.groupId);
    });
    this.getGroupKaNaam();
    this.getGroupDetails();
    this.getGroupKeMembers();
  }
  getGroupDetails(): void {
    this.backService.showAllGroupExpense(this.groupId).subscribe(
      (response) => {
        console.log('asdfgsdf', response);
        this.expensesList = response;
        this.getGroupDetails = response;
        console.log('qwertyuio', this.getGroupDetails);
      },
      (error) => {
        console.error('Error fetching details:', error);
      }
    );
  }

  getGroupKaNaam(): void {
    this.backService.showGroupKaName(this.groupId).subscribe(
      (response) => {
        // console.log(this.groupId);
        console.log(response, 'gdxfcgyhuijkoihugyxfcgvhb');
        this.groupName = response;
        console.log(this.groupName);
      },
      (error) => {
        console.error('Error fetching group name:', error);
      }
    );
  }

  addMembersToGroup(): void {
    this.backService.addMembersToGroup(this.groupId,this.membersToAdd).subscribe(
      (response) => {
          console.log(response);
          this.membersToAdd=[];
          
          
      },
      (error) => {
        console.error("Error adding member:", error);
      }
    );
  }

  getGroupKeMembers(): void {
    this.backService.showGroupKeMembers(this.groupId).subscribe(
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
