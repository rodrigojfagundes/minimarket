import { Component, OnInit } from '@angular/core';
import { Category } from '../category';

@Component({
    selector: 'app-category-form',
    templateUrl: './category-form.component.html',
    styleUrls: ['./category-form.component.css']
})
export class CategoryFormComponent implements OnInit {

    category: Category;

    constructor() {}

    ngOnInit(): void {
        
    }
}