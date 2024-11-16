import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Person } from '../models/Person';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { Token } from '../models/Token';
// import { Token } from '@angular/compiler';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  loggedInSubject = new BehaviorSubject(sessionStorage.getItem("token") !== null);
  adminSubject = new Subject();

  constructor(private http: HttpClient) { }

  // registrate(person: Person): Observable<Token> {
  //   return this.http.post<Token>("http://localhost:8080/signup", person);
  // }

  login(email: string, password: string): Observable<Token> {
    return this.http.post<Token>("http://localhost:8080/login", {email, password});
  }

  admin(): Observable<boolean> {
    return this.http.get<boolean>("http://localhost:8080/admin", 
      {headers: {"Authorization": "Bearer " + (sessionStorage.getItem("token") || "")     }}
    );
  }

  signup(person: Person): Observable<Token> {
    return this.http.post<Token>("http://localhost:8080/signup", person);
  }
}
