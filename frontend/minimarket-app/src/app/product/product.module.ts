import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { ProductRoutingModule } from "./product-routing.module";
import { ProductFormComponent } from "./product-form/product-form.component";
import { FormsModule } from "@angular/forms";


@NgModule({
    declarations:[ProductFormComponent],
    imports:[
        CommonModule,
        ProductRoutingModule,
        FormsModule
    ],
    exports:[
        ProductFormComponent
    ]
})
export class ProductModule {}