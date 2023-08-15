import { Component, OnInit } from '@angular/core';
import { StudentClientService } from '../client/student-client.service';
import { Attendance } from './model/attendance.model';

@Component({
  selector: 'app-attendance',
  templateUrl: './attendance.component.html',
  styleUrls: ['./attendance.component.css']
})
export class AttendanceComponent implements OnInit {
  attendances: Attendance[] = null;

  constructor(private studentClient: StudentClientService) { }

  ngOnInit(): void {
    this.studentClient.getAttendances().subscribe({
      next: attendances => this.attendances = attendances
    })
  }

}
