import { NgModule } from   '@angular/core';
import { CommonModule } from '@angular/common';

import { CategoryRoutingModule } from './category-routing.module'; 
import { CategoryFormComponent } from './category-form/category-form.component';

@NgModule({
    declarations: [CategoryFormComponent],
    imports:[
        CommonModule,
        CategoryRoutingModule
    ],
    exports:[
        CategoryFormComponent
    ]
})
export class CategoryModule { }
