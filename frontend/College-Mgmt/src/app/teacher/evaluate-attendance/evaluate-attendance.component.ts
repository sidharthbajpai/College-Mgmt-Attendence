import { Component, OnInit } from '@angular/core';
import { TeacherClientService } from '../client/teacher-client.service';
import { AttendanceDetail } from './model/attendance-details.model';

@Component({
  selector: 'app-evaluate-attendance',
  templateUrl: './evaluate-attendance.component.html',
  styleUrls: ['./evaluate-attendance.component.css']
})
export class EvaluateAttendanceComponent implements OnInit {
  attendances: AttendanceDetail[]
  attendanceDate: string = new Date().toISOString().split('T')[0];
  isSubmitted: boolean = false;

  constructor(private teacherClient: TeacherClientService) { }

  ngOnInit(): void {
    this.teacherClient.getAttendanceForDate(this.attendanceDate).subscribe({
      next: attendances => {
        this.attendances = attendances; console.log(attendances);
      }
    });
  }

  onDateSelected(date: string) {
    this.isSubmitted = false;
    this.teacherClient.getAttendanceForDate(date).subscribe({
      next: attendances => {
        this.attendances = attendances; console.log(attendances);
      }
    });
  }

  onSubmit()  {
    this.isSubmitted = true;
    this.teacherClient.submitAttendanceForDate(this.attendanceDate, this.attendances).subscribe();
  }

}
