import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { AuthenticationService } from 'src/app/auth/services/authentication.service';

@Injectable()
export class CanEnterGuard implements CanActivate {

  constructor(
    private auth: AuthenticationService,
    private router: Router
  ){}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

      //o tap é um operador do rxjs que utiliza pra consumir o dados que retorna de um pipe, ele não modifica o valor.
    return this.auth.logged().pipe(tap(item => {
      if(item == false){
        this.router.navigateByUrl('/auth/login')
      }
    }));
  }
  
}
