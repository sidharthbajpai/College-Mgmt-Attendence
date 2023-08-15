import { Component, OnDestroy, OnInit } from '@angular/core';
import { StudentClientService } from '../student/client/student-client.service';
import { LoginService } from '../login/service/login.service';
import { Subscription, exhaustMap, take, tap } from 'rxjs';
import { LoginDetails } from '../login/model/login-details.model';
import { TeacherClientService } from '../teacher/client/teacher-client.service';
import { AdminClientService } from '../admin/client/admin-client.service';
import { UserDetails } from './model/user-details.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {
  userDetails: UserDetails;

  constructor(
    private loginService: LoginService,
    private studentClient: StudentClientService,
    private teacherClient: TeacherClientService,
    private adminClient: AdminClientService) { }

  dataSub: Subscription = null;

  ngOnInit(): void {
    this.loginService.loginDetails.pipe(take(1)).subscribe({
      next: loginDetails => {
        this.initializeData(loginDetails);
      }
    });
  }

  initializeData(loginDetails: LoginDetails) {
    if (loginDetails.role === 'student') {
      this.dataSub = this.studentClient.getStudentDetails().subscribe({
        next: studentDetails => this.userDetails = studentDetails
      });
    } else if (loginDetails.role === 'teacher') {
      this.dataSub = this.teacherClient.getTeacherDetails().subscribe({
        next: teacherDetails => this.userDetails = teacherDetails
      });
    } else if (loginDetails.role === 'admin') {
      this.dataSub = this.adminClient.getAdminDetails().subscribe({
        next: adminDetails => this.userDetails = adminDetails
      });
    }
  }

  ngOnDestroy(): void {
      this.dataSub.unsubscribe();
  }
}
