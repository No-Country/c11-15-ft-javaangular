export interface User {
  id: string;
  email: string;
  password: string;
  accountDetails: AccountDetails;
  /* token: string */
}

export interface CreateUser extends Omit<User, 'id'> {}

export interface RecoverUser extends Omit<User, 'id' | 'password' | 'accountDetails'> {}

export interface PasswordUser extends Omit<User, 'id' | 'accountDetails'> {}

export interface AccountDetails {
  fullName: string
}
