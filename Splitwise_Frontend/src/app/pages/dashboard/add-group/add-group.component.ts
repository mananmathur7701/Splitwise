// import { Component, Output, EventEmitter, OnInit } from '@angular/core';
// import { BackServicesService } from 'src/app/back-services.service';


// @Component({
//   selector: 'app-add-group',
//   templateUrl: './add-group.component.html',
//   styleUrls: ['./add-group.component.css']
// })


// export class AddGroupComponent implements OnInit{
//   groups: any;
//   backService: any;
//   id = localStorage.getItem("id");

//   ngOnInit(): void
//   {
//    this.addGroup;    
//   }

//   onSubmit(formData: any){
//     console.log(formData.value);

//     this.addGroup(formData.value.groupName);
       
//   }
  
//   addGroup(groupName: string) {
//     this.backService.addNewGroup(String(this.id), this.groupName).subscribe(
//       (response: any) =>{
//         console.log(this.id);
//         this.groups=response;
//         console.log(this.groups);
//       },
//       (error: any) => {
//         console.error('Error fetching groups:', error);
//       }
//     );
//     throw new Error('Method not implemented.');
//   }

//   groupName(id: any, groupName: any) {}


//   @Output() closeModal: EventEmitter<void> = new EventEmitter<void>();

//   onCloseClick() {
//     this.closeModal.emit(); // Emit an event when the close button is clicked
//   }
// }




import { Component, Output, EventEmitter } from '@angular/core';
import { RouterLink } from '@angular/router';
import { BackServicesService } from 'src/app/back-services.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-add-group',
  templateUrl: './add-group.component.html',
  styleUrls: ['./add-group.component.css']
})
export class AddGroupComponent {
  groupData: any = { groupName: '' }; // Define groupData object with groupName property
  id = localStorage.getItem("id");
  groups: any;

  constructor(private backService: BackServicesService, private router: Router) {}


  onSubmit(): void {
    this.addGroup(this.groupData.groupName);
    this.router.navigate(['/dashboard/group-details',this.groups.id]);
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
  }
}
