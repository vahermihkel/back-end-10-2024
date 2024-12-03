import { Component } from '@angular/core';
import { OrderRow } from '../models/OrderRow';
import { FormsModule } from '@angular/forms';
import { OrderService } from '../services/order.service';
import { Order } from '../models/Order';
import { RouterLink } from '@angular/router';
import { ParcelMachinesComponent } from "./parcel-machines/parcel-machines.component";
import { PaymentComponent } from './payment/payment.component';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [FormsModule, RouterLink, 
    ParcelMachinesComponent, PaymentComponent,
    DatePipe
  ], // HTMLs olevad eriasjad
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {
  date = new Date();
  cart: OrderRow[] = [];
  isLoggedIn = sessionStorage.getItem("token") !== null;
  // view = "cart";
  // email = "";

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

  // changeView(newView: string) {
  //   this.view = newView;
  // }

  

}
