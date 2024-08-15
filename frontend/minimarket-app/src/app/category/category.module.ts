import { NgModule } from   '@angular/core';
import { CommonModule } from '@angular/common';

import { CategoryRoutingModule } from './category-routing.module'; 
import { CategoryFormComponent } from './category-form/category-form.component';
import { FormsModule } from '@angular/forms';
import { CategoryListComponent } from './category-list/category-list.component';

@NgModule({
    declarations: [CategoryFormComponent, CategoryListComponent],
    imports:[
        CommonModule,
        CategoryRoutingModule,
        FormsModule
    ],
    exports:[
        CategoryFormComponent,
        CategoryListComponent
    ]
})
export class CategoryModule { }
