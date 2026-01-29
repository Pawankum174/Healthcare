import { Routes } from '@angular/router';
import { AuthGuard } from '../app/Auth/guard';
import { LoginComponent } from './login/components';
import { DashboardComponent } from './dashboard/dashboard';

const routes: Routes = 
[ { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] },
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];
export { routes };