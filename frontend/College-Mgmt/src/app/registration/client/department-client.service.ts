import { HttpClient } from "@angular/common/http"; 
import { Injectable } from "@angular/core";
import { Department } from "../model/department.model";

@Injectable()
export class DepartmentClientService {
    constructor(private httpClient: HttpClient) {}

    private url: string = 'http://localhost:8080/department';

    getAllDepartments() {
        return this.httpClient.get<Department[]>(this.url);
    }
}