import { style } from '@angular/animations';
import { Component, ElementRef, ViewChild } from '@angular/core';

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.css']
})
export class PaymentsComponent {
  amountToBePaid : any = localStorage.getItem("settlementAmount");
 
  @ViewChild("paymentRef", {static:true}) paymentRef!: ElementRef;

  ngOnInit(): void{
    console.log(window.paypal);
    window.paypal.Buttons().render(this.paymentRef.nativeElement); 
    console.log(localStorage.getItem("settlementAmount"));
    
    
  }

}
