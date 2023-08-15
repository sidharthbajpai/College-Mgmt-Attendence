import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { UserDetails } from "src/app/home/model/user-details.model";
import { Attendance } from "../attendance/model/attendance.model";

@Injectable({providedIn: 'root'})
export class StudentClientService {
    constructor(private httpClient: HttpClient)  {}

    getStudentDetails() {
        return this.httpClient.get<UserDetails>('http://localhost:8080/student');
    }

    getAttendances() {
        return this.httpClient.get<Attendance[]>('http://localhost:8080/student/attendance');
    }
}