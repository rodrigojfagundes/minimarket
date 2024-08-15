import { Component, OnInit } from "@angular/core";
import { Product } from "../product";

@Component({
    selector: 'app-product-form',
    templateUrl: './product-form.component.html',
    styleUrls:['./product-form.component.css']

})
export class ProductFormComponent implements OnInit {

    product: Product;
    
    constructor(){}

    ngOnInit(): void {
        
    }
}