
@Injectable({ providedIn: 'root' })
export class AuthService {
  private tokenKey = 'mednex_token';

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<void> {
    return this.http.post<{ token: string }>('/api/auth/login', { username, password })
      .pipe(tap(response => localStorage.setItem(this.tokenKey, response.token)));
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  getRole(): string {
    const token = this.getToken();
    if (!token) return '';
    const decoded: any = jwtDecode(token);
    return decoded.role;
  }

  getTenant(): string {
    const token = this.getToken();
    if (!token) return '';
    const decoded: any = jwtDecode(token);
    return decoded.tenant;
  }
}
