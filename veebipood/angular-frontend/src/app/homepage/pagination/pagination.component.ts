import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-pagination',
  standalone: true,
  imports: [],
  templateUrl: './pagination.component.html',
  styleUrl: './pagination.component.css'
})
export class PaginationComponent {
  page = 0;
  @Input() totalPages = 0;
  @Output() pageChangeFetch: EventEmitter<number> = new EventEmitter<number>();


  previousPage() {
    this.page--;
    this.pageChangeFetch.emit(this.page);
  }

  nextPage() {
    this.page++;
    this.pageChangeFetch.emit(this.page);
  }
}
