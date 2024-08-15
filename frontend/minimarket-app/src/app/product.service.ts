import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Product } from "./product/product";
import { Observable } from "rxjs";
import { ResponsePageableProduct } from "./product/response-pageable-product.model";

@Injectable({
    providedIn: 'root'
})

export class ProductService {


    httpOptions = {
        Headers: new HttpHeaders({
            'Content-Type' : 'application/json'
        })
    };

    constructor(private http: HttpClient){}

    insert(product: Product) : Observable<Product>{
        return this.http.post<Product>(`http://localhost:8080/products`, product);
    }

    update(product: Product) : Observable<any>{
        return this.http.put<Product>(`http://localhost:8080/products/${product.id}`, product);
    }

    findAll() : Observable<ResponsePageableProduct> {
        return this.http.get<ResponsePageableProduct>('http://localhost:8080/products');
    }

    findById(id: number) : Observable<Product> {
        return this.http.get<any> (`http://localhost:8080/products/$id`);
    }

    delete(product: Product) : Observable<any>{
        return this.http.delete<any>(`http://localhost:8080/products/${'product.id'}`);
    }


}
