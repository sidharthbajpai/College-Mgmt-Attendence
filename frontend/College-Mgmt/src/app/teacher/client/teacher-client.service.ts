import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { UserDetails } from "src/app/home/model/user-details.model";
import { AttendanceDetail } from "../evaluate-attendance/model/attendance-details.model";

@Injectable({providedIn: 'root'})
export class TeacherClientService {
    constructor(private httpClient: HttpClient)  {}

    getTeacherDetails() {
        return this.httpClient.get<UserDetails>('http://localhost:8080/teacher');
    }

    getAttendanceForDate(date: string) {
        return this.httpClient.get<AttendanceDetail[]>('http://localhost:8080/teacher/attendance', {
            params: new HttpParams().append('date', date)
        });
    }

    submitAttendanceForDate(date: string, attendances: AttendanceDetail[]) {
        return this.httpClient.put('http://localhost:8080/teacher/attendance',
        attendances, {
            params: new HttpParams().append('date', date)
        });
    }
}