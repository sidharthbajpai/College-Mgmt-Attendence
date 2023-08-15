import { Component, Input, OnInit } from '@angular/core';
import { Request } from '../model/request.model';
import { AdminClientService } from '../../client/admin-client.service';

@Component({
  selector: '[app-request-tr]',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.css']
})
export class RequestComponent implements OnInit {
  @Input() request: Request;

  constructor(private adminClient: AdminClientService) { }

  ngOnInit(): void {
  }

  onAccept() {
    this.adminClient.evaluateRequest(this.request.requestId, true).subscribe({
      next: r => this.request.status = 'ACCEPTED'
    });
  }

  onDeny() {
    this.adminClient.evaluateRequest(this.request.requestId, false).subscribe({
      next: r => this.request.status = 'DENIED'
    });
  }

}
