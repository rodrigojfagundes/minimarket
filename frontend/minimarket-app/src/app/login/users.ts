import { Role } from '../role/role';

export class User {
  name: string;
  username: string;
  password: string;
  roles: Role[];
}
