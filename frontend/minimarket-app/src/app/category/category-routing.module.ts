import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { CategoryFormComponent } from './category-form/category-form.component';
import { CategoryListComponent } from './category-list/category-list.component';

const routes: Routes = [
    { path: 'category-form', component: CategoryFormComponent },
    { path: 'category-form/:id', component: CategoryFormComponent },
    { path: 'category-list', component: CategoryListComponent }
];


@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class CategoryRoutingModule { }