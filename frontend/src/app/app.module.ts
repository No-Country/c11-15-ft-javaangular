import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { SwiperModule } from 'swiper/angular';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavComponent } from './website/components/nav/nav.component';
import { LoginComponent } from './website/pages/login/login.component';
import { RegisterComponent } from './website/pages/register/register.component';
import { NotfoundComponent } from './website/pages/notfound/notfound.component';
import { HomeComponent } from './website/pages/home/home.component';
import { ImgComponent } from './website/components/img/img.component';
import { PetComponent } from './website/components/pet/pet.component';
import { PetallComponent } from './website/components/petall/petall.component';
import { TimeInterceptor } from './interceptors/time.interceptor';
import { TokenInterceptor } from './interceptors/token.interceptor';
import { CookieService } from 'ngx-cookie-service';
import { CategoryComponent } from './website/pages/category/category.component';
import { MylistpetComponent } from './website/pages/mylistpet/mylistpet.component';
import { RecoveryComponent } from './website/pages/recovery/recovery.component';
import { ProfileComponent } from './website/pages/profile/profile.component';
import { PetDetailComponent } from './website/pages/pet-detail/pet-detail.component';
import { LayoutComponent } from './website/layout/layout.component';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    LoginComponent,
    RegisterComponent,
    NotfoundComponent,
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
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    SwiperModule
  ],
  providers: [
    CookieService,
    { provide: HTTP_INTERCEPTORS, useClass: TimeInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
