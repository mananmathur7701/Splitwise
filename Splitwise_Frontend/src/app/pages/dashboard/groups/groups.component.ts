import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { BackServicesService } from 'src/app/back-services.service';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.css'],
})
export class GroupsComponent implements OnInit {
  @Output() groupCreated: EventEmitter<void> = new EventEmitter<void>();

  groups!: any[];
  id: any = localStorage.getItem('id');
  mylist!: any[];
  owed: number | any;
  owes: number | any;
  balance: number | any;

  createGroup() {
    
  }
  showModal = false;
  toggleModal() {
    this.showModal = !this.showModal;
  }

  closeModalHandler() {
    this.showModal = false; // Close the modal
  }

  constructor(private backService: BackServicesService) {}

  ngOnInit(): void {
    // this.addGroup();
    this.getGroups();
    //  this.addGroup();
    this.udhariKaData();
  }

  getGroups(): void {
    this.backService.groupsOfUser(this.id).subscribe(
      (response) => {
        console.log(this.id);
        this.groups = response;
        console.log(this.groups);
      },
      (error) => {
        console.error('Error fetching groups:', error);
      }
    );
  }

  lenaDenaBalance(): void {
    this.backService.lenaDenaBalance(this.id).subscribe(
      (response) => {
        console.log();
        this.groups = response;
        console.log();
      },
      (error) => {
        console.error('error fetching data', error);
      }
    );
  }

  udhariKaData() {
    this.backService.dashboardKaData(this.id).subscribe(
      (data) => {
        console.log(data);
        this.mylist = data;
        this.owed = this.mylist[0];
        this.owes = this.mylist[1];
        this.balance = this.mylist[2];
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
