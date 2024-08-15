import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Category } from "./category/category";
import { Observable } from "rxjs";
import { ResponsePageable } from "./category/response-pageable.model";

@Injectable({
    providedIn: 'root'
})

export class CategoryService {


    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json'
        })
    };

    constructor(private http: HttpClient) {}



    insert(category: Category) : Observable<Category>{
        return this.http.post<Category>(`http://localhost:8080/categories`, category)
    }

    update(category: Category) : Observable<any>{
        return this.http.put<Category>(`http://localhost:8080/categories/${category.id}`, category);
    }


    findAll() : Observable <ResponsePageable> {
        return this.http.get<ResponsePageable>('http://localhost:8080/categories');
    }

    findById(id: number) : Observable<Category> {
        return this.http.get<any>  (`http://localhost:8080/categories/${id}`)
    }

    delete(category: Category) : Observable<any>{
        return this.http.delete<any>(`http://localhost:8080/categories/${category.id}`);
    }
}
