import { Component, OnInit } from '@angular/core';
import { BackServicesService } from 'src/app/back-services.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{
  home!: any[];
  id: any = localStorage.getItem("id");
  constructor(
    private backService: BackServicesService
  ){};

  ngOnInit(): void {
  this.getHome();
}
  getHome(): void {
    this.backService.expensesOfUserWhereOwed(this.id).subscribe(
      (response) => {
        console.log(this.id);
        this.home= response;
        console.log(this.home);

        this.backService.expensesOfUserWhereOwes(this.id).subscribe(
          (response) => {
            console.log(this.id);
            this.home=response;
            console.log(this.home);
          }
        )
      },
      (error) => {
        console.error("Error fetching details:", error);
      }
    );
  }


}
