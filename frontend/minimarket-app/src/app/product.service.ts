import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Product } from "./product/product";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})

export class ProductService {

    constructor(private http: HttpClient){}

    insert(product: Product) : Observable<Product>{
        return this.http.post<Product>(`http://localhost:8080/products`, product);
    }

    update(product: Product) : Observable<any>{
        return this.http.put<Product>(`http://localhost:8080/products/${product.id}`, product);
    }

    findAll() : Observable<Product[]> {
        return this.http.get<Product[]>('http://localhost:8080/products');
    }

    findById(id: number) : Observable<Product> {
        return this.http.get<any> (`http://localhost:8080/products/$id`);
    }

    delete(product: Product) : Observable<any>{
        return this.http.delete<any>(`http://localhost:8080/products/${'product.id'}`);
    }


}
