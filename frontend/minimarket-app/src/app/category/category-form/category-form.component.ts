import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Category } from '../category';
import { CategoryService } from 'src/app/category.service';
import { Observable } from 'rxjs';
import { from } from 'rxjs';

@Component({
    selector: 'app-category-form',
    templateUrl: './category-form.component.html',
    styleUrls: ['./category-form.component.css']
})
export class CategoryFormComponent implements OnInit {

    category: Category;
    success: boolean = false;
    errors: String[];
    id: number;


    constructor(
        private service: CategoryService,
        private router: Router,
        private activatedRoute: ActivatedRoute
    ) {
        this.category = new Category();
    }

    
    ngOnInit(): void {
        let params: Observable<Params> = this.activatedRoute.params
        params.subscribe(urlParams => {
            this.id = urlParams['id'];
            if(this.id){
                this.service.findById(this.id).subscribe(response => this.category = response, 
                    errorResponse => this.category = new Category()
                )
            }
        })

    }

    returnToList(){
        this.router.navigate(['/category-list'])
    }


    onSubmit(){
        if(this.id){
            this.service
                .update(this.category).subscribe(response => {
                    this.success = true;
                    this.errors = null;
                },
            errorResponse => {
                this.errors = ['Error update category']
            })
        } else {
            this.service.insert(this.category)
            .subscribe(response => {
                this.success = true;
                this.errors = null;
                this.category = response;
            }, errorResponse => {
                this.success = false;
                this.errors = errorResponse.error.errors;
            })
        }
    }


}