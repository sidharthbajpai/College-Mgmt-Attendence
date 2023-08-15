import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, exhaustMap, take, tap } from "rxjs";
import { LoginService } from "../service/login.service";

@Injectable()
export class AuthInterceptorService implements HttpInterceptor {

    constructor(private loginService: LoginService) {}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        return this.loginService.loginDetails.pipe(
            take(1),
            exhaustMap(loginDetails => {
                if(!loginDetails) {
                    return next.handle(req);
                }
                let authorizationHeader: string = 'Basic ' + window.btoa(loginDetails.username + ':' + loginDetails.password);
                const modifiedReq = req.clone({headers: new HttpHeaders().set('Authorization', authorizationHeader)});
                return next.handle(modifiedReq);
            })
        );

        let httpHeaders: HttpHeaders = new HttpHeaders();

        if(sessionStorage.getItem('authorizationHeader')){
            let authorizationHeader: string = JSON.parse(sessionStorage.getItem('authorizationHeader'));
            httpHeaders = httpHeaders.append('Authorization', authorizationHeader);
        }
        const authReq = req.clone({
            headers: httpHeaders
        })
        return next.handle(req);
    }

}