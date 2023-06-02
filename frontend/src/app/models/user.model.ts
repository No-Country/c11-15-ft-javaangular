export interface User {
  id: string;
  email: string;
  password: string;
  accountDetails: AccountDetails;
  /* token: string */
}

export interface CreateUser extends Omit<User, 'id'> {}


export interface AccountDetails {
  fullName: string
}
