import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../Auth/Service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username = '';
  password = '';
  tenant = '';

  constructor(private authService: AuthService, private router: Router) {}

  onLogin(): void {
    this.authService.login(this.username, this.password).subscribe(() => {
      const role = this.authService.getRole();
      if (role === 'ADMIN') this.router.navigate(['/admin']);
      else if (role === 'DOCTOR') this.router.navigate(['/doctor']);
      else if (role === 'NURSE') this.router.navigate(['/nurse']);
    });
  }
}
