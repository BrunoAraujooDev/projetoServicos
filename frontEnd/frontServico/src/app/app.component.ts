import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from './auth/services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  logged!: boolean

  constructor(
    private authService: AuthenticationService,
    private router:Router
  ){}

    ngOnInit(){

      this.authService.logged().subscribe((item) => {
        this.logged = item
      })
    }

    logout(): void{
      this.authService.logout()
      this.router.navigateByUrl('/auth/login')
    }

}
