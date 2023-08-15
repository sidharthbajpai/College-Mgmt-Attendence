import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { LoginFormComponent } from './login/login-form/login-form.component';
import { HeaderComponent } from './header/header.component';
import { RegistrationComponent } from './registration/registration.component';
import { FormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { HomeComponent } from './home/home.component';
import { AuthInterceptorService } from './login/interceptor/auth-intercepter.service';
import { AttendanceComponent } from './student/attendance/attendance.component';
import { RequestComponent } from './admin/requests/request/request.component';
import { EvaluateAttendanceComponent } from './teacher/evaluate-attendance/evaluate-attendance.component';
import { RequestsComponent } from './admin/requests/requests.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LoginFormComponent,
    HeaderComponent,
    RegistrationComponent,
    HomeComponent,
    AttendanceComponent,
    RequestComponent,
    EvaluateAttendanceComponent,
    RequestsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
