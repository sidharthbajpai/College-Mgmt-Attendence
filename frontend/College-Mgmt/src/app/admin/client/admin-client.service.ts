import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { UserDetails } from "src/app/home/model/user-details.model";

@Injectable({ providedIn: 'root' })
export class AdminClientService {
    constructor(private httpClient: HttpClient) { }

    getAdminDetails() {
        return this.httpClient.get<UserDetails>('http://localhost:8080/admin');
    }

    getRegistrationRequests() {
        return this.httpClient.get<Request[]>('http://localhost:8080/admin/requests');
    }

    evaluateRequest(requestId: number, isAccepted: boolean) {
        return this.httpClient.put('http://localhost:8080/admin/requests/evaluate', {},
            {
                params: new HttpParams().appendAll({
                    'requestId': requestId,
                    'acceptRequest': isAccepted
                })
            });
    }
}