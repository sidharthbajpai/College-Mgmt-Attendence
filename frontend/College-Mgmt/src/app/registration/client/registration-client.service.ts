import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { RegistrationDetails } from "../model/registration-details.model";

@Injectable()
export class RegistrationClientService {
    constructor(private httpClient: HttpClient) {}

    url: string = 'http://localhost:8080/register'

    register(registrationDetails: RegistrationDetails) {
        return this.httpClient.post(this.url, registrationDetails);
    }  
}