import { Component } from '@angular/core';
import { OrderRow } from '../models/OrderRow';
import { FormsModule } from '@angular/forms';
import { OrderService } from '../services/order.service';
import { Order } from '../models/Order';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [FormsModule], // HTMLs olevad eriasjad
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {
  cart: OrderRow[] = [];
  view = "cart";
  email = "";

  constructor(private orderService: OrderService) {} // TS-s olevad teised failid

  ngOnInit(): void {
    this.cart = JSON.parse(localStorage.getItem("cart") || "[]");
  }

  decreaseQuantity(index: number) {
    this.cart[index].pcs--;
    if (this.cart[index].pcs === 0) {
      this.cart.splice(index,1);
    }
    localStorage.setItem("cart", JSON.stringify(this.cart));
  }

  increaseQuantity(index: number) {
    this.cart[index].pcs++;
    localStorage.setItem("cart", JSON.stringify(this.cart));
  }

  removeFromCart(index: number) {
    this.cart.splice(index,1);
    localStorage.setItem("cart", JSON.stringify(this.cart));
  }

  calculateTotal() {
    let sum = 0;
    this.cart.forEach(orderRow => {
      sum += orderRow.product.price * orderRow.pcs;
    });
    return sum;
  }

  changeView(newView: string) {
    this.view = newView;
  }

  sendOrderToBE() {
    const order: Order = {
      person: {email: this.email},
      orderRows: this.cart
    };
    this.orderService.saveOrder(order).subscribe();
  }
}
