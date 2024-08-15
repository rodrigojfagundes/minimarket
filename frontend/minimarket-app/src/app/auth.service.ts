import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { JwtConfig, JwtHelperService } from '@auth0/angular-jwt';
import { User } from './login/user';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  apiURL: string = environment.apiUrlBase + '/users';

  tokenURL: string = environment.apiUrlBase + environment.getTokenURL;
  clientID: string = environment.clientId;
  clientSecret: string = environment.clientSecret;
  jwtHelper: JwtHelperService = new JwtHelperService();

  constructor(private http: HttpClient) {}

  getToken() {
    const tokenString = localStorage.getItem('access_token');
    if (tokenString) {
      const token = JSON.parse(tokenString).access_token;
      return token;
    }
    return null;
  }
}
