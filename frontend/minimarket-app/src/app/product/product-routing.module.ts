import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { ProductFormComponent } from './product-form/product-form.component'


const routes: Routes = [
    { path: 'product-form', component: ProductFormComponent }
];


@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ProductRoutingModule{ }