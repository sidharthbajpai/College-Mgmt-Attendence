import { Injectable } from "@angular/core";
import { ActivatedRoute, ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable, map, take } from "rxjs";
import { LoginService } from "../service/login.service";

@Injectable({ providedIn: 'root' })
export class LoginGuard implements CanActivate {
    constructor(private loginService: LoginService, private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
        return this.loginService.loginDetails.pipe(
            take(1),
            map(loginDetails => {
                if (loginDetails) {
                    this.loginService.logout();
                }
                return true;
            }));
    }

}