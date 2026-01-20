import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { Observable } from 'rxjs';
import jwtDecode from 'jwt-decode';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private tokenKey = 'mednex_token';

  constructor(private http: HttpClient) {}

  login(username: string, password: string, tenant: string): Observable<void> {
    return this.http.post<{ token: string }>('/api/auth/login', { username, password, tenant })
      .pipe(tap(response => localStorage.setItem(this.tokenKey, response.token)));
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  getRole(): string {
    const token = this.getToken();
    if (!token) return '';
    const decoded: any = jwtDecode(token);
    return decoded.role || '';
  }

  getUsername(): string {
    const token = this.getToken();
    if (!token) return '';
    const decoded: any = jwtDecode(token);
    return decoded.username || '';
  }

  logout(): void {
    localStorage.removeItem(this.tokenKey);
  }
