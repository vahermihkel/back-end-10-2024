import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SupplierService {

  constructor(private http: HttpClient) { }

  getSupplierProducts(): Observable<any> {
    return this.http.get<any>("http://localhost:8080/supplier");
  }

  getSupplierEscuelaProducts(): Observable<any> {
    return this.http.get<any>("http://localhost:8080/supplier-escuela");
  }
}
