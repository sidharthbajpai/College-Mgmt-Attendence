import { Component, OnInit } from '@angular/core';
import { AdminClientService } from '../client/admin-client.service';

@Component({
  selector: 'app-requests',
  templateUrl: './requests.component.html',
  styleUrls: ['./requests.component.css']
})
export class RequestsComponent implements OnInit {
  requests: Request[];

  constructor(private adminClient: AdminClientService) { }

  ngOnInit(): void {
    this.adminClient.getRegistrationRequests().subscribe({
      next: requests => this.requests = requests
    })
  }

}
