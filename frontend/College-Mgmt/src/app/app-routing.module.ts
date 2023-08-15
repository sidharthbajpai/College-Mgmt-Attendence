import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './login/guard/auth.guard';
import { AttendanceComponent } from './student/attendance/attendance.component';
import { EvaluateAttendanceComponent } from './teacher/evaluate-attendance/evaluate-attendance.component';
import { RoleGuard } from './login/guard/role.guard';
import { LoginGuard } from './login/guard/login.guard';
import { RequestsComponent } from './admin/requests/requests.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'login', component: LoginComponent, canActivate:  [LoginGuard] },
  { path: 'registration', component: RegistrationComponent },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'attendance', component: AttendanceComponent, canActivate: [RoleGuard]},
  { path: 'evaluate-attendance', component: EvaluateAttendanceComponent, canActivate: [RoleGuard]},
  { path: 'requests', component: RequestsComponent, canActivate: [RoleGuard]},
  { path: '**', redirectTo: '/home'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
