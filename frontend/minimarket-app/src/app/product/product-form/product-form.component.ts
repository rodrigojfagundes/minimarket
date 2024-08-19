import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { Category } from 'src/app/category/category';
import { CategoryService } from 'src/app/category.service';
import { ProductService } from 'src/app/product.service';
import { Observable } from 'rxjs';
import { ActivatedRoute, Params, Router } from '@angular/router';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css'],
})
export class ProductFormComponent implements OnInit {
  product: Product;
  categories: Category[];
  success: boolean;
  errors: String[];
  id: number;

  constructor(
    private service: ProductService,
    private categoryService: CategoryService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.product = new Product();
  }

  returnToList() {
    this.router.navigate(['/product/list']);
  }

  ngOnInit(): void {
    let params: Observable<Params> = this.activatedRoute.params;
    params.subscribe((urlParams) => {
      this.id = urlParams['id'];
      if (this.id) {
        this.service
          .findById(this.id)
          .subscribe((response) => (this.product = response));
        console.log(this.product);
      }
      this.categoryService.findAll().subscribe((data) => {
        this.categories = data.content;
      });
    });
  }

  //    ngOnInit(): void {
  //        let params: Observable<Params> = this.activatedRoute.params
  //        params.subscribe(urlParams => {
  //            this.id = urlParams['id'];
  //            if(this.id){
  //                this.service.findById(this.id).subscribe(
  //              response => this.category = response,
  //                    errorResponse => this.category = new
  //                         Category()
  //                )
  //            }
  //        })
  //
  //   }

  onSubmit() {
    if (this.id) {
      this.service.update(this.product).subscribe(
        (response) => {
          this.success = true;
          this.errors = null;
        },
        (errorResponse) => {
          this.errors = ['Error update product'];
        }
      );
      return;
    }
    this.service.insert(this.product).subscribe(
      (response) => {
        this.success = true;
        this.errors = null;
        this.product = new Product();
      },
      (errorResponse) => {
        this.success = false;
        this.errors = errorResponse.error.errors;
      }
    );
  }
}
