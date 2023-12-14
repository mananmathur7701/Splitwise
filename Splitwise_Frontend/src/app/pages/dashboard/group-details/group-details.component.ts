import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BackServicesService } from 'src/app/back-services.service';
import { forkJoin } from 'rxjs';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-group-details',
  templateUrl: './group-details.component.html',
  styleUrls: ['./group-details.component.css'],
})
export class GroupDetailsComponent implements OnInit {

  @ViewChild('nameForm') nameForm!: NgForm;
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
  //router: any;
  expenseId: any;
  expenseDetails={
    amountPaid:0,
    comment: '',
    groupName:'',
    id:0,
    spentAt:''
  };
  expenseSplitDetailsList:any[] = [];
  paymentSplitDetailsList:any[] = [];


  


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
    this.nameForm.resetForm();
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
    private route: ActivatedRoute,
    private router: Router
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
    console.log('getGroup expenses');
    
    this.backService.showAllGroupExpense(this.groupId).subscribe(
      (response) => {
        console.log('asdfgsdf', response);
        this.expensesList = response;
        // console.log('qwertyuio', this.getGroupDetails);
      },
      (error) => {
        console.error('Error fetching details:', error);
      }
    );
  }

  getGroupKaNaam(): void {
    console.log("get group name");
    
    this.backService.showGroupKaName(this.groupId).subscribe(
      (response) => {
        // console.log(this.groupId);
        // console.log(response, 'gdxfcgyhuijkoihugyxfcgvhb');
        this.groupName = response;
        console.log(" aja bhai naam", response)
        
        // console.log(this.groupName);
      },
      (error) => {
        console.error('Error fetching group name:', error);
      }
    );
  }

  getGroupKeMembers(): void {
    console.log('get group members');
    
    this.backService.showGroupKeMembers(this.groupId).subscribe(
      (response) => {
        // console.log(this.groupId);
        console.log(response, 'gdxfcgyhuijkoihugyxfcgvhb');
        this.members = response;
        // console.log('sadfgds',this.members);
      },
      (error) => {
        console.error('Error fetching group members:', error);
      }
    );
  }

  addMembersToGroup(): void {
    this.backService.addMembersToGroup(this.groupId,this.membersToAdd).subscribe(
      (response) => {
          console.log(response);
          // this.router.navigate(['/dashboard/groups']);
          this.toggleModal();
          this.getGroupKeMembers();
          this.membersToAdd=[];
      },
      (error) => {
        console.error("Error adding member:", error);
        Swal.fire({
          icon: "error",
          title: "Oops...",
          text: "Please ask your friend to create an account to add them to the group.",
          // footer: '<a href="#">Why do I have this issue?</a>'
        });
        
        this.toggleModal();
        this.getGroupKeMembers();  
        
          
      }
    );
  }



  removeMemberFromGroup(userEmail: string,): void{
    // console.log(userEmail,this.groupId,'sjdhbviewbsfciwbiwbsciwebfciwebfifcbiewf');
    
    const confirmDelete = window.confirm('Are you sure you want to remove this member?');

    if (confirmDelete) {
    this.backService.removeMemberFromGroup(userEmail,this.groupId).subscribe(
      (response) => {
        console.log(response);
        this.getGroupKeMembers();

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
        this.getGroupKaNaam();
        this.toggleEditModal();
        
      },
      (error) => {
        console.error("Error changing group's name:", error);
        this.toggleEditModal();
        this.getGroupKaNaam();
        
      }
    );
  }



  deleteGroup(): void {
    // const confirmDelete = window.confirm('Are you sure you want to delete this group?');
    Swal.fire({
      title: "Are you sure?",
      text: "You won't be able to revert this!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, delete it!"
    }).then((result) => {
      if (result.isConfirmed) {
        this.backService.deleteEntireGroup(this.groupId).subscribe(
          (response) => {
            console.log(response);
            Swal.fire({
              title: "Deleted!",
              text: "Your file has been deleted.",
              icon: "success"
            });
            this.router.navigate(['/dashboard/groups'],{ relativeTo: this.route });
            // Handle successful group deletion, if needed
          },
          (error) => {
            console.error('Error deleting group:', error);
            // Handle error while deleting group, if needed
          }
        );
        
      }
    });  
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
        // console.log(response.expenseDetails);
        this.expenseDetails = response.expenseDetails;
        // console.log(this.expenseDetails.comment);
        this.expenseSplitDetailsList = response.expenseSplitDetails;
        this.paymentSplitDetailsList = response.paymentSplitDetails;
        
        // Access individual responses from 'response.expenseDetails', 'response.expenseSplitDetails', 'response.paymentSplitDetails'
        // this.expenseDetails = response.expenseDetails;
        // Do further processing or assignment of data
      },
      (error) => {
        console.error('Error fetching details:', error);
      }
    );
  }


}
