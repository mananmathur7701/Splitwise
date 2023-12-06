import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BackServicesService } from 'src/app/back-services.service';
import { forkJoin } from 'rxjs';

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
  showEditModal = false;
  showExpenseModal = false;
  members: any[] = []; // Array to store members
  membersToAdd: any[] = []; // Array to store the members to be added
  groupName: string = '';
  expensesList: any[] = [];
  router: any;
  expenseId: any;
  expenseDetails: any;


  


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
  toggleEditModal() {
    this.showEditModal = !this.showEditModal;
  }

  closeEditModalHandler() {
    this.showEditModal = false; // Close the modal
  }

  closeExpenseModalHandler() {
    this.showExpenseModal = false; // Close the modal
  }

  toggleExpenseModal(id:any) {
    this.showExpenseModal = !this.showExpenseModal;
    this.getExpenseDetails(id);
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
    this.getGroupDetails();
    this.getGroupKaNaam();
    this.getGroupKeMembers();
    // this.getExpenseDetails();
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
    console.log(this.groupId,'plkmk');
    
    this.backService.showGroupKaName(this.groupId).subscribe(
      (response) => {
        // console.log(this.groupId);
        console.log(response, 'gdxfcgyhuijkoihugyxfcgvhb');
        this.groupName = response;
        console.log(" aja bhai naam", response)
        return response;
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

  removeMemberFromGroup(userEmail: string,): void{
    console.log(userEmail,this.groupId,'sjdhbviewbsfciwbiwbsciwebfciwebfifcbiewf');
    
    const confirmDelete = window.confirm('Are you sure you want to remove this member?');

    if (confirmDelete) {
    this.backService.removeMemberFromGroup(userEmail,this.groupId).subscribe(
      (response) => {
        console.log(response);

      },
      (error) => {
        console.error("Error removing member:", error);
      }
    );
    }
  }

  changeGroupKaName(newGroupName: string): void {
    this.backService.changeGroupKaName(this.groupId, newGroupName).subscribe(
      (response) => {
        console.log(response);
        this.showEditModal = false; // Close the modal after successful update
        // Optionally, update 'groupName' variable if needed
        this.groupName = newGroupName;
      },
      (error) => {
        console.error("Error changing group's name:", error);
      }
    );
  }



  deleteGroup(): void {
    const confirmDelete = window.confirm('Are you sure you want to delete this group?');
  
    if (confirmDelete) {
      this.backService.deleteEntireGroup(this.groupId).subscribe(
        (response) => {
          console.log(response);
          // Handle successful group deletion, if needed
        },
        (error) => {
          console.error('Error deleting group:', error);
          // Handle error while deleting group, if needed
        }
      );
    }
  }

  
  deleteEntireGroup():void{
    this.backService.deleteEntireGroup(this.groupId).subscribe(
      (response) =>{
        console.log(response);

      },
      (error) => {
        console.error("Error deleting group:", error);
      }
    );
  }

  getExpenseDetails(expenseId:any): void {
    console.log(expenseId,'plkmk');
    
    const expenseDetails$ = this.backService.showExpenseKaDetails(expenseId);
    const expenseSplitDetails$ = this.backService.expenseDetailsKeLiyeExpenseSplitKaData(expenseId);
    const paymentSplitDetails$ = this.backService.expenseDetailsKeLiyePaymentSplitKaData(expenseId);
  
    forkJoin({
      expenseDetails: expenseDetails$,
      expenseSplitDetails: expenseSplitDetails$,
      paymentSplitDetails: paymentSplitDetails$
    }).subscribe(
      (response) => {
        console.log(response);
        // Access individual responses from 'response.expenseDetails', 'response.expenseSplitDetails', 'response.paymentSplitDetails'
        this.expenseDetails = response.expenseDetails;
        // Do further processing or assignment of data
      },
      (error) => {
        console.error('Error fetching details:', error);
      }
    );
  }


}
