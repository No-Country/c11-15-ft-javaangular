import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SwiperModule } from 'swiper/angular';

import { WebsiteRoutingModule } from './website-routing.module';

import { CategoryComponent } from './pages/category/category.component';
import { HomeComponent } from './pages/home/home.component';
import { ImgComponent } from './components/img/img.component';
import { LayoutComponent } from './layout/layout.component';
import { LoginComponent } from './pages/login/login.component';
import { MylistpetComponent } from './pages/mylistpet/mylistpet.component';
import { NavComponent } from './components/nav/nav.component';
import { PetDetailComponent } from './pages/pet-detail/pet-detail.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { RegisterComponent } from './pages/register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { PetallComponent } from './components/petall/petall.component';
import { PetComponent } from './components/pet/pet.component';

import { RecoveryComponent } from './pages/recovery/recovery.component';


@NgModule({
  declarations: [
    NavComponent,
    LoginComponent,
    RegisterComponent,

    HomeComponent,
    ImgComponent,
    PetComponent,
    PetallComponent,
    CategoryComponent,
    MylistpetComponent,
    RecoveryComponent,
    ProfileComponent,
    PetDetailComponent,
    LayoutComponent
  ],
  imports: [
    CommonModule,
    WebsiteRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    SwiperModule
  ]
})
export class WebsiteModule { }
