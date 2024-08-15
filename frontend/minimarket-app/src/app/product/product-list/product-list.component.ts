import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { Product } from '../product';
import { ProductService } from 'src/app/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css'],
})
export class ProductListComponent implements OnInit {
  products: Product[];
  productSelected: Product;
  messageSuccess: string;
  messageError: string;

  constructor(private service: ProductService, private router: Router) {}

  ngOnInit(): void {
    this.findAll();
  }

  findAll() {
    this.service.findAll().subscribe((data) => {
      this.products = data.content;
    });
  }

  insert() {
    this.router.navigate(['/product/form']);
  }

  prepareDelete(product: Product) {
    this.productSelected = product;
  }

  delete() {
    this.service.delete(this.productSelected).subscribe(
      (response) => {
        this.messageSuccess = 'Product has been delete with success';
        this.ngOnInit();
      },
      (error) => (this.messageError = 'Error in delete product')
    );
  }
}
