import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProductFormComponent } from './product-form/product-form.component';
import { ProductListComponent } from './product-list/product-list.component';
import { LayoutComponent } from '../layout/layout.component';
import { AuthGuard } from '../auth.guard';

const routes: Routes = [
  {
    path: 'product',
    component: LayoutComponent,
    canActivate: [AuthGuard],
    children: [
      { path: 'form', component: ProductFormComponent },
      { path: 'form/:id', component: ProductFormComponent },
      { path: 'list', component: ProductListComponent },
      { path: '', redirectTo: 'product/list', pathMatch: 'full' },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ProductRoutingModule {}
