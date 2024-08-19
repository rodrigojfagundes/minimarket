import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Product } from './product/product';
import { Observable } from 'rxjs';
import { ResponsePageableProduct } from './product/response-pageable-product.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  apiURL: string = environment.apiUrlBase + '/products';

  httpOptions = {
    Headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  constructor(private http: HttpClient) {}

  insert(product: Product): Observable<Product> {
    return this.http.post<Product>(`${this.apiURL}`, product);
  }

  update(product: Product): Observable<any> {
    return this.http.put<Product>(`${this.apiURL}/${product.id}`, product);
  }

  findAll(): Observable<ResponsePageableProduct> {
    return this.http.get<ResponsePageableProduct>(this.apiURL);
  }

  findById(id: number): Observable<Product> {
    return this.http.get<any>(`${this.apiURL}/${id}`);
  }

  delete(product: Product): Observable<any> {
    return this.http.delete<any>(`${this.apiURL}/${product.id}`);
  }
}
