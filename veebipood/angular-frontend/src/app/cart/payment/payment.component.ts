import { Component, Input } from '@angular/core';
import { Order } from '../../models/Order';
import { OrderService } from '../../services/order.service';
import { OrderRow } from '../../models/OrderRow';

@Component({
  selector: 'app-payment',
  standalone: true,
  imports: [],
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.css'
})
export class PaymentComponent {
  @Input("cartInput") cart: OrderRow[] = [];

  constructor(private orderService: OrderService
  ) {} // TS-s olevad teised failid


  sendOrderToBE() {
    const order: Order = {
      // person: {email: this.email},
      orderRows: this.cart
    };
    if (sessionStorage.getItem("token") === null) {
      return;
    }
    this.orderService.saveOrder(order).subscribe(res => {
      window.location.href = res.link;
    });
  }
}
