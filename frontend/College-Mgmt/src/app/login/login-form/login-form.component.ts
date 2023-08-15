import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../service/login.service';
import { LoginDetails } from '../model/login-details.model';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {
  defaultRole: String = 'student';
  loginUnsuccessful: boolean = false;
  errorMessage: String;

  constructor(private router: Router, private activeRoute: ActivatedRoute, private loginService: LoginService) { }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm) {
    let loginDetails: LoginDetails = new LoginDetails(form.value.username, form.value.password, form.value.role);
    this.loginService.login(loginDetails).subscribe(
      res => {
        this.loginUnsuccessful = false;
        this.router.navigate(['./home']);
      },
      err => {
        this.loginUnsuccessful = true;
        if (err instanceof HttpErrorResponse) {
          switch (err.status) {
            case 401:
              this.errorMessage = 'Invalid Credentials!';
              break;
            case 403:
              this.errorMessage = 'Please select the correct type!';
              break;
            default:
              this.errorMessage = 'An error occured!';
              break;
          }
        } else {
          this.errorMessage = 'An error occured!';
        }
      }
    );
  }

  onRegistration() {
    this.router.navigate(['../registration'], { relativeTo: this.activeRoute });
  }

}
