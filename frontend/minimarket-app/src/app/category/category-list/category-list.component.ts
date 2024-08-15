import { Component, OnInit } from "@angular/core";
import { Route, Router } from "@angular/router";
import { Category } from "../category";
import { CategoryService } from "src/app/category.service";

@Component({
    selector: 'app-category-list',
    templateUrl: './category-list.component.html',
    styleUrls: ['./category-list.component.css']
})


export class CategoryListComponent implements OnInit {

    categories: Category[];
    categorySelected: Category;
    messageSuccess: string;
    messageError: string;


    constructor(
        private service: CategoryService,
        private router: Router) {}


        ngOnInit(): void {
            this.findAll();
        }


        findAll(){
            this.service.findAll().subscribe(data => {
                this.categories = data.content;
            })
        }

        insert(){
          this.router.navigate(['/category-form'])  
        }

        prepareDelete(category: Category){
            this.categorySelected = category;
        }

        delete(){
            this.service.delete(this.categorySelected).subscribe(response => {
                this.messageSuccess = 'Category has been delete with success'
                this.ngOnInit();
            },
        error =>this.messageError = 'Error in delete category'
        )
    }

}
