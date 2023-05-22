import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { HomeComponent } from './pages/home/home.component';
import { CategoryComponent } from './pages/category/category.component';
import { MylistpetComponent } from './pages/mylistpet/mylistpet.component';
import { RecoveryComponent } from './pages/recovery/recovery.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { PetDetailComponent } from './pages/pet-detail/pet-detail.component';
import { LayoutComponent } from './layout/layout.component';

import { AuthGuard } from '../guards/auth.guard';

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
        path: 'category/:id',
        component: CategoryComponent,
        pathMatch: 'full',
        title: 'categoria',
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
        canActivate: [ AuthGuard ],
        component: ProfileComponent,
        pathMatch: 'full',
        title: 'profile',
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
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class WebsiteRoutingModule {}
