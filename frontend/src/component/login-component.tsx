@Component({
  selector: 'app-login',
  template: `
    <form (ngSubmit)="onLogin()">
      <mat-form-field>
        <input matInput placeholder="Username" [(ngModel)]="username" name="username" required>
      </mat-form-field>
      <mat-form-field>
        <input matInput type="password" placeholder="Password" [(ngModel)]="password" name="password" required>
      </mat-form-field>
      <button mat-raised-button color="primary" type="submit">Login</button>
    </form>
  `
})
export class LoginComponent {
  username = '';
  password = '';

  constructor(private authService: AuthService, private router: Router) {}

  onLogin() {
    this.authService.login(this.username, this.password).subscribe(() => {
      const role = this.authService.getRole();
      if (role === 'ADMIN') this.router.navigate(['/admin']);
      else if (role === 'DOCTOR') this.router.navigate(['/doctor']);
      else if (role === 'NURSE') this.router.navigate(['/nurse']);
    });
  }
}
