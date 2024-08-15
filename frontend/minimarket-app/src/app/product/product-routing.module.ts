import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { ProductFormComponent } from './product-form/product-form.component';
import { ProductListComponent } from './product-list/product-list.component';


const routes: Routes = [
    { path: 'product-form', component: ProductFormComponent },
    { path: 'product-form/:id', component: ProductFormComponent },
    { path: 'product-list', component: ProductListComponent }
];


@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ProductRoutingModule{ }