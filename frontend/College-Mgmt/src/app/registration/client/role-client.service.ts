import { HttpClient } from "@angular/common/http"; 
import { Injectable } from "@angular/core";
import { Role } from "../model/role.model";

@Injectable()
export class RoleClientService {
    constructor(private httpClient: HttpClient) {}

    private url: string = 'http://localhost:8080/role';

    getAllRoles() {
        return this.httpClient.get<Role[]>(this.url);
    }
}