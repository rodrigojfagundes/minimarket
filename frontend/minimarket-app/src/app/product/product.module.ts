import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { ProductRoutingModule } from "./product-routing.module";
import { ProductFormComponent } from "./product-form/product-form.component";


@NgModule({
    declarations:[],
    imports:[
        CommonModule,
        ProductRoutingModule
    ],
    exports:[
        ProductFormComponent
    ]
})
export class ProductModule {}