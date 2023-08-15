import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RoleClientService } from "./client/role-client.service";
import { DepartmentClientService } from "./client/department-client.service";
import { Role } from "./model/role.model";
import { Department } from "./model/department.model";
import { RegistrationClientService } from './client/registration-client.service';
import { RegistrationDetails } from './model/registration-details.model';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
  providers: [RoleClientService, DepartmentClientService, RegistrationClientService]
})
export class RegistrationComponent implements OnInit {
  @ViewChild('f') registrationForm: NgForm;

  roles: Role[] = [];
  departments: Department[] = [];
  isRegistered: boolean = false;

  constructor(private router: Router, private activeRoute: ActivatedRoute, private roleClient: RoleClientService, private deptClient: DepartmentClientService, private registrationClient: RegistrationClientService) { }

  ngOnInit(): void {
    this.roleClient.getAllRoles().subscribe({
      next: roles => this.roles = roles
    });
    this.deptClient.getAllDepartments().subscribe({
      next: departments => this.departments = departments
    })
  }

  onLogin() {
    this.router.navigate(['../login'], { relativeTo: this.activeRoute });
  }

  onRegister() {
    let registrationDetails = new RegistrationDetails(
      this.registrationForm.value['firstName'],
      this.registrationForm.value['lastName'],
      this.registrationForm.value['userName'],
      this.registrationForm.value['password'],
      this.registrationForm.value['phoneNumber'],
      this.registrationForm.value['location'],
      this.registrationForm.value['email'],
      this.registrationForm.value['role']
    );
    if (!this.isAdminSelected()) {
      registrationDetails.departmentId = this.registrationForm.value['department'];
    }
    this.registrationClient.register(registrationDetails).subscribe({
      next: () => {
        this.onReset();
        this.isRegistered = true;
      }
    });
  }

  onReset() {
    this.registrationForm.reset();
    this.isRegistered = false;
  }

  isAdminSelected() {
    let role: Role = this.roles.find(r => r.roleId == this.registrationForm.value['role']);
    return role ? role.roleName === 'ADMIN' : false;
  }

}
