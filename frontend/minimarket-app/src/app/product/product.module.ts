import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { ProductRoutingModule } from "./product-routing.module";
import { ProductFormComponent } from "./product-form/product-form.component";
import { FormsModule } from "@angular/forms";
import { ProductListComponent } from './product-list/product-list.component';


@NgModule({
    declarations:[ProductFormComponent, ProductListComponent],
    imports:[
        CommonModule,
        ProductRoutingModule,
        FormsModule
    ],
    exports:[
        ProductFormComponent,
        ProductListComponent
    ]
})
export class ProductModule {}