import { NgModule } from   '@angular/core';
import { CommonModule } from '@angular/common';

import { CategoryRoutingModule } from './category-routing.module'; 
import { CategoryFormComponent } from './category-form/category-form.component';
import { FormsModule } from '@angular/forms';

@NgModule({
    declarations: [CategoryFormComponent],
    imports:[
        CommonModule,
        CategoryRoutingModule,
        FormsModule
    ],
    exports:[
        CategoryFormComponent
    ]
})
export class CategoryModule { }
