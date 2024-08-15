import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { User } from './users';
import { AuthService } from '../auth.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Role } from '../role/role';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  name: string;
  username: string;
  password: string;
  registering: boolean;
  messageSuccess: string;
  errors: String[];
  roles: Role[];

  constructor(private router: Router, private authService: AuthService) {}

  onSubmit() {
    this.authService.tryLogin(this.username, this.password).subscribe(
      (response) => {
        const access_token = JSON.stringify(response);
        localStorage.setItem('access_token', access_token);
        this.router.navigate(['/home']);
      },
      (HttpErrorResponse) => {
        this.errors = ['User and/or password unknow'];
      }
    );
  }

  cancelRegister() {
    this.registering = false;
  }

  register() {
    const user: User = new User();
    user.name = this.name;
    user.username = this.username;
    user.password = this.password;
    user.roles = [{ id: 2 }];

    this.authService.insert(user).subscribe(
      (response) => {
        this.messageSuccess = 'Register with success';
        this.registering = false;
        this.name = '';
        this.username = '';
        this.password = '';
        this.roles = [];
        this.errors = [];
      },
      (errorResponse) => {
        this.messageSuccess = null;
        this.errors = errorResponse.error.errors;
      }
    );
  }
}
