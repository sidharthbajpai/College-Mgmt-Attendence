import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { LoginDetails } from "../model/login-details.model";
import { BehaviorSubject, tap } from "rxjs";
import { Router } from "@angular/router";

@Injectable({ providedIn: 'root' })
export class LoginService {
    loginDetails = new BehaviorSubject<LoginDetails>(null);

    constructor(private httpClient: HttpClient, private router: Router) { }

    login(loginDetails: LoginDetails) {
        this.loginDetails.next(loginDetails);
        localStorage.setItem('loginDetails', JSON.stringify(loginDetails));
        return this.httpClient.get('http://localhost:8080/' + loginDetails.role);
    }

    autoLogin() {
        const loginDetails: LoginDetails = JSON.parse(localStorage.getItem('loginDetails'));
        if(!loginDetails) {
            return;
        }
        this.loginDetails.next(loginDetails);
    }

    logout() {
        this.loginDetails.next(null);
        this.router.navigate(['/login']);
        localStorage.removeItem('loginDetails');
    }
}