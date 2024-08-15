import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { CategoryFormComponent } from './category-form/category-form.component';

const routes: Routes = [
    { path: 'category-form', component: CategoryFormComponent }
];


@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class CategoryRoutingModule { }