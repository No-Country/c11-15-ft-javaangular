import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { HomeComponent } from './pages/home/home.component';
import { MylistpetComponent } from './pages/mylistpet/mylistpet.component';
import { RecoveryComponent } from './pages/recovery/recovery.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { PetDetailComponent } from './pages/pet-detail/pet-detail.component';
import { LayoutComponent } from './layout/layout.component';

import { AuthGuard } from '../guards/auth.guard';
import { FormComponent } from './pages/form/form.component';
import { FormInfoComponent } from './pages/form-info/form-info.component';
import { AutenticacionComponent } from './pages/autenticacion/autenticacion.component';
import { RecorpasswordComponent } from './pages/recorpassword/recorpassword.component';
import { ChangepasswordComponent } from './pages/changepassword/changepassword.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      {
        path: '',
        redirectTo: '/home',
        pathMatch: 'full',
      },
      {
        path: 'home',
        component: HomeComponent,
        title: 'home',
      },
      {
        path: 'pet/:id',
        component: PetDetailComponent,
        pathMatch: 'full',
        title: 'pet detail',
      },
      {
        path: 'mylistpet',
        component: MylistpetComponent,
        pathMatch: 'full',
        title: 'my list pet',
      },
      {
        path: 'recovery',
        component: RecoveryComponent,
        pathMatch: 'full',
        title: 'recovery',
      },
      {
        path: 'profile',
        canActivate: [AuthGuard],
        component: ProfileComponent,
        pathMatch: 'full',
        title: 'profile',
      },
      {
        path: 'form-info',
        component: FormInfoComponent,
        pathMatch: 'full',
        title: 'formulario',
      },
      {
        path: 'form',
        canActivate: [AuthGuard],
        component: FormComponent,
        pathMatch: 'full',
        title: 'formulario',
      },
    ],
  },
  {
    path: 'login',
    component: LoginComponent,
    pathMatch: 'full',
    title: 'login',
  },
  {
    path: 'register',
    component: RegisterComponent,
    pathMatch: 'full',
    title: 'register user',
  },
  {
    path: 'recoverypassword',
    component: RecorpasswordComponent,
    pathMatch: 'full',
    title: 'recoverypassword',
  },
  {
    path: 'changepassword/:correo',
    component: ChangepasswordComponent,
    pathMatch: 'full',
    title: 'changepassword',
  },
  {
    path: 'verify/:codeVerification',
    component: AutenticacionComponent,
    pathMatch: 'full',
    title: 'Verification',
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class WebsiteRoutingModule {}
