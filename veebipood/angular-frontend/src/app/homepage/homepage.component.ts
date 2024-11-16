import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { Product } from '../models/Product';
import { OrderRow } from '../models/OrderRow';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [RouterLink, FormsModule],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css'
})
export class HomepageComponent implements OnInit {
  products: Product[] = [];
  search = "";
  page = 0;
  totalPages = 0;
  totalElements = 0;

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.fetchProducts();
  }

  previousPage() {
    this.page--;
    this.fetchProducts();
  }

  nextPage() {
    this.page++;
    this.fetchProducts();
  }

  fetchProducts() {
    this.productService.getProducts(this.page, 3).subscribe(response => {
      this.products = response.content;
      this.totalPages = response.totalPages;
      this.totalElements = response.totalElements;
    })
  }

  addToCart(productClicked: Product): void {
    const cartLS: OrderRow[] = JSON.parse(localStorage.getItem("cart") || "[]");
    const index = cartLS.findIndex(orderRow => orderRow.product.id === productClicked.id)
    if (index !== -1) {
      // suurendan kogust --> kui on varasemalt ostukorvis
      cartLS[index].pcs++;
    } else {
      // pushin --> siis kui pole varasemalt ostukorvis
      cartLS.push({pcs: 1, product: productClicked});
    }
    localStorage.setItem("cart", JSON.stringify(cartLS));
  }

  searchFromProducts() {
    this.productService.getProductsByName(this.search).subscribe(response => 
      this.products = response.content
    )
  }
}
