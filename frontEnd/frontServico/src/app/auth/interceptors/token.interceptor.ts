import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { catchError, Observable, of } from 'rxjs';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(
    private router: Router,
    private auth: AuthenticationService
  ) {}

  // ele serve pra interceptar a requisição e passar o header pra ela
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let token = localStorage.getItem('token')

    if(token == null){
      token = ''
    }

    const req = request.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    })

    return next.handle(req).pipe(
      catchError(error => {
        // o instanceof é uma palavra reservada do typescrypt, que verifica se a variavel é do tipo de uma classe(se ela é a instancia de uma classe)
        if(error instanceof HttpErrorResponse){
          if(error.status == 403){
            this.auth.logout()
            this.router.navigateByUrl('/auth/login')
          }
        }
        //o of é um observable que retorna dados
        return of(error)
      })
    );
  }
}
