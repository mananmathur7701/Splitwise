import { Component, Output, EventEmitter, OnInit } from '@angular/core';
import { BackServicesService } from 'src/app/back-services.service';


@Component({
  selector: 'app-add-group',
  templateUrl: './add-group.component.html',
  styleUrls: ['./add-group.component.css']
})


export class AddGroupComponent implements OnInit{
  groups: any;
  backService: any;
  id = localStorage.getItem("id");

  ngOnInit(): void
  {
   this.addGroup();    
  }

  onSubmit(formData:any){
    console.log(formData.value);
       
  }
  
  addGroup() {
    this.backService.addNewGroup(String(this.id), this.groupName).subscribe(
      (response: any) =>{
        console.log(this.id);
        this.groups=response;
        console.log(this.groups);
      },
      (error: any) => {
        console.error('Error fetching groups:', error);
      }
    );
    throw new Error('Method not implemented.');
  }

  groupName(id: any, groupName: any) {}


  @Output() closeModal: EventEmitter<void> = new EventEmitter<void>();

  onCloseClick() {
    this.closeModal.emit(); // Emit an event when the close button is clicked
  }
}






