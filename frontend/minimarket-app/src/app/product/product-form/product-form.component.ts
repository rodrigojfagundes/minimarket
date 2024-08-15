import { Component, OnInit } from "@angular/core";
import { Product } from "../product";
import { Category } from "src/app/category/category";
import { CategoryService } from "src/app/category.service";
import { ProductService } from "src/app/product.service";

@Component({
    selector: 'app-product-form',
    templateUrl: './product-form.component.html',
    styleUrls:['./product-form.component.css']

})
export class ProductFormComponent implements OnInit {

    product: Product;
    categories: Category[];
    success: boolean;
    errors: String[];

    
    constructor(
        private service: ProductService,
        private categoryService: CategoryService
    ){
        this.product = new Product();
    }

    ngOnInit(): void {
        this.categoryService
            .findAll().subscribe(data => {
                this.categories = data.content;
            });
    }

    onSubmit(){
        this.service
        .insert(this.product)
        .subscribe(response => {
            this.success = true;
            this.errors = null;
            this.product = new Product();
        }, errorResponse => {
            this.success = false;
            this.errors = errorResponse.error.errors;
        })
    }
}