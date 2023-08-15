import { Component, OnDestroy, OnInit } from '@angular/core';
import { LoginService } from '../login/service/login.service';
import { Subscription } from 'rxjs';
import { LoginDetails } from '../login/model/login-details.model';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy {
  private loginSub: Subscription;
  isAuthenticated: boolean = false;
  role: string;
  constructor(private loginService: LoginService) { }

  ngOnInit(): void {
    this.loginSub = this.loginService.loginDetails.subscribe(user => {
      if(user) {
        this.isAuthenticated = true;
        this.role = user.role;
      } else {
        this.isAuthenticated = false;
      }
    });
  }

  onLogout() {
    this.loginService.logout();
  }

  ngOnDestroy(): void {
    this.loginSub.unsubscribe();
  }

}
