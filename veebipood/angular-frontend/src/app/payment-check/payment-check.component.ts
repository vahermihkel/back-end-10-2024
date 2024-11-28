import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OrderService } from '../services/order.service';

@Component({
  selector: 'app-payment-check',
  standalone: true,
  imports: [],
  templateUrl: './payment-check.component.html',
  styleUrl: './payment-check.component.css'
})
export class PaymentCheckComponent {
  // localhost:4200/payment?order_reference=123006&payment_reference=f7932eb74d8e4a7fc8c624075a070af6334120e2759422920fdfc0b3ad7a54cc
  // localhost:4200/payment?order_reference=123003&payment_reference=226e52c40b97287972fdc254dbbe73398309806d4df539fd0a81b3d1285bb20f
  // localhost:4200/payment?order_reference=1238766&payment_reference=5732b8e15953f87a64464b0b5882ee0b2b83fc9bd0d4aface60763f0c0b44cb4
  paidStatus = "";

  constructor(private route: ActivatedRoute, private orderService: OrderService) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(res => {
      const paymentReference = res["payment_reference"];
      this.orderService.checkPayment(paymentReference).subscribe(res => {
        this.paidStatus = res.status; // settled, cancelled, voided
      });
    })
    
  }
}
