export interface User {
  id: string;
  email: string;
  password: string;
  /* token: string */
}

export interface CreateUser extends Omit<User, 'id'> {}
