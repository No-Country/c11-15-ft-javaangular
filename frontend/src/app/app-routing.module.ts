import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './website/pages/login/login.component';
import { NotfoundComponent } from './website/pages/notfound/notfound.component';
import { RegisterComponent } from './website/pages/register/register.component';
import { HomeComponent } from './website/pages/home/home.component';
import { CategoryComponent } from './website/pages/category/category.component';
import { MylistpetComponent } from './website/pages/mylistpet/mylistpet.component';
import { RecoveryComponent } from './website/pages/recovery/recovery.component';
import { ProfileComponent } from './website/pages/profile/profile.component';
import { PetDetailComponent } from './website/pages/pet-detail/pet-detail.component';

const routes: Routes = [
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
    component: ProfileComponent,
    pathMatch: 'full',
    title: 'profile',
  },
  {
    path: '**',
    component: NotfoundComponent,
    pathMatch: 'full',
    title: 'Not Found',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
