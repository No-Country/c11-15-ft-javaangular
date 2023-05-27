export interface User {
  id: string;
  email: string;
  password: string;
  name: string;
  /* token: string */
}

export interface CreateUser extends Omit<User, 'id'> {}
