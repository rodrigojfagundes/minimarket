import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CategoryFormComponent } from './category-form/category-form.component';
import { CategoryListComponent } from './category-list/category-list.component';
import { AuthGuard } from '../auth.guard';
import { LayoutComponent } from '../layout/layout.component';

const routes: Routes = [
  {
    path: 'category',
    component: LayoutComponent,
    canActivate: [AuthGuard],
    children: [
      { path: 'form', component: CategoryFormComponent },
      { path: 'form/:id', component: CategoryFormComponent },
      { path: 'list', component: CategoryListComponent },
      { path: '', redirectTo: '/category/list', pathMatch: 'full' },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CategoryRoutingModule {}
