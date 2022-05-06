import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { UsuarioJWT } from '../models/usuario-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private readonly baseUrl: string = "http://localhost:8080/"
  private logged$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false)

  constructor(
    private http: HttpClient
  ) { }

  login(user: UsuarioJWT): Observable<{Authorization: string}>{
    return this.http.post<{Authorization: string}>(`${this.baseUrl}login`, user)
    .pipe(
      tap(
        (token) => {
          localStorage.setItem('token', token.Authorization)
          this.logged$.next(true)
        }
      )
    )
  }

  logout(){
    localStorage.removeItem('token')
    this.logged$.next(false)
  }

  logged(): Observable<boolean>{

    this.logged$.next( localStorage.getItem('token') !=  null)

    return this.logged$.asObservable()
  }

}
