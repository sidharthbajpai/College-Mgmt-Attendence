import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable, map, take } from "rxjs";
import { LoginService } from "../service/login.service";

@Injectable({ providedIn: 'root' })
export class RoleGuard implements CanActivate {
    constructor(private loginService: LoginService, private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
        return this.loginService.loginDetails.pipe(
            take(1),
            map(loginDetails => {
                if (loginDetails) {
                        let path = route.url[0].path;
                        console.log(path);
                        
                        if(path === 'attendance' && loginDetails.role === 'student') {
                            return true;
                        } else if(path === 'evaluate-attendance' && loginDetails.role === 'teacher') {
                            return true;
                        } else if(path === 'requests' && loginDetails.role === 'admin') {
                            return true;
                        } else {
                            return this.router.createUrlTree(['/login']);;
                        }
                } else {
                    return this.router.createUrlTree(['/login']);
                }
            }));
    }

}