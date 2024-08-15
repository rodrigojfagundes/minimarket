import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Category } from './category/category';
import { Observable } from 'rxjs';
import { ResponsePageable } from './category/response-pageable.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  apiURL: string = environment.apiUrlBase + '/categories';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  constructor(private http: HttpClient) {}

  insert(category: Category): Observable<Category> {
    return this.http.post<Category>(`${this.apiURL}`, category);
  }

  update(category: Category): Observable<any> {
    return this.http.put<Category>(`${this.apiURL}/${category.id}`, category);
  }

  findAll(): Observable<ResponsePageable> {
    return this.http.get<ResponsePageable>(this.apiURL);
  }

  findById(id: number): Observable<Category> {
    return this.http.get<any>(`${this.apiURL}/${id}`);
  }

  delete(category: Category): Observable<any> {
    return this.http.delete<any>(`${this.apiURL}/${category.id}`);
  }
}
