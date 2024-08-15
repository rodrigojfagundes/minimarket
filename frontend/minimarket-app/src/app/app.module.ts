import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { TemplateModule } from './template/template.module';
import { HomeComponent } from './home/home.component';
import { CategoryModule } from './category/category.module';
import { CategoryService } from './category.service';
import { ProductModule } from './product/product.module';
import { ProductService } from './product.service';
import { LoginComponent } from './login/login.component.ts';
import { LayoutComponent } from './layout/layout.component.ts';
import { AuthService } from './auth.service';
import { TokenInterceptor } from './token.interceptor';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [AppComponent, HomeComponent, LoginComponent, LayoutComponent],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    TemplateModule,
    CategoryModule,
    ProductModule,
  ],
  providers: [
    CategoryService,
    ProductService,
    AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
